package com.example.finanzas.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.finanzas.data.CuentaBancaria
import kotlin.collections.map

private enum class AnimatedState { START, END }

@Composable
fun AnimatedDonutChart(
    cuentas: List<CuentaBancaria>,
    modifier: Modifier = Modifier
) {
    val transitionState = remember {
        MutableTransitionState(AnimatedState.START).apply { targetState = AnimatedState.END }
    }
    val transition = rememberTransition(transitionState, label = "donutTransition")

    val angleProgress by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 1200, easing = FastOutSlowInEasing) },
        label = "angleProgress"
    ) { state ->
        if (state == AnimatedState.END) 360f else 0f
    }

    Canvas(modifier = modifier.size(300.dp)) {
        // Optimización idiomática para el cálculo del total
        val total = cuentas.sumOf { it.balance.toDouble() }.toFloat().takeIf { it > 0f } ?: 1f
        var startAngle = -90f
        val strokeWidth = 40.dp.toPx()
        val diameter = size.minDimension - strokeWidth
        val sizeRect = Size(diameter, diameter)
        val topLeftOffset = Offset((size.width - diameter) / 2f, (size.height - diameter) / 2f)

        cuentas.forEach { cuenta ->
            val proportion = cuenta.balance / total
            val sweep = proportion * angleProgress
            drawArc(
                color = cuenta.color,
                startAngle = startAngle,
                sweepAngle = sweep,
                useCenter = false,
                topLeft = topLeftOffset,
                size = sizeRect,
                style = Stroke(width = strokeWidth)
            )
            startAngle += sweep
        }
    }
}
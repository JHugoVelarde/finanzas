package com.example.finanzas.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedBarChart(
    values: List<Float>,
    colors: List<Color>,
    modifier: Modifier = Modifier
) {
    var startAnimation by remember { mutableStateOf(false) }
    val animationProgress by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "barAnimation"
    )

    LaunchedEffect(Unit) { startAnimation = true }

    Canvas(modifier = modifier.fillMaxWidth().height(200.dp)) {
        if (values.isEmpty()) return@Canvas

        val maxValue = values.maxOrNull() ?: 1f
        val barWidth = size.width / (values.size * 2f)
        val spacing = barWidth

        values.forEachIndexed { index, value ->
            val barHeight = (value / maxValue) * size.height * animationProgress
            val xOffset = (index * (barWidth + spacing)) + (spacing / 2f)
            val yOffset = size.height - barHeight

            drawRoundRect(
                color = colors.getOrElse(index) { Color.Gray },
                topLeft = Offset(xOffset, yOffset),
                size = Size(barWidth, barHeight),
                cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx())
            )
        }
    }
}
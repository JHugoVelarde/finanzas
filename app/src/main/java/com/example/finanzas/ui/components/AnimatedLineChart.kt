package com.example.finanzas.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedLineChart(
    data: List<Float>,
    modifier: Modifier = Modifier,
    lineColor: Color = MaterialTheme.colorScheme.primary
) {
    var animationProgress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(key1 = data) {
        animate(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing)
        ) { value, _ -> animationProgress = value }
    }

    Canvas(modifier = modifier.fillMaxWidth().height(200.dp)) {
        if (data.isEmpty()) return@Canvas

        val maxData = data.maxOrNull() ?: 0f
        val minData = data.minOrNull() ?: 0f
        val range = (maxData - minData).takeIf { it > 0f } ?: 1f
        val stepX = size.width / (data.size - 1).coerceAtLeast(1)

        val path = Path().apply {
            val startY = size.height - ((data.first() - minData) / range) * size.height
            moveTo(0f, startY)

            for (i in 1 until data.size) {
                val currentX = (i - 1) * stepX
                val currentY = size.height - ((data[i - 1] - minData) / range) * size.height
                val nextX = i * stepX
                val nextY = size.height - ((data[i] - minData) / range) * size.height

                val controlX1 = currentX + (nextX - currentX) / 2f
                val controlX2 = currentX + (nextX - currentX) / 2f

                cubicTo(
                    x1 = controlX1, y1 = currentY,
                    x2 = controlX2, y2 = nextY,
                    x3 = nextX, y3 = nextY
                )
            }
        }

        val fillPath = Path().apply {
            addPath(path)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }

        clipRect(right = size.width * animationProgress) {
            drawPath(
                path = fillPath,
                brush = Brush.verticalGradient(
                    colors = listOf(lineColor.copy(alpha = 0.5f), Color.Transparent)
                )
            )
            drawPath(
                path = path,
                color = lineColor,
                style = Stroke(width = 4.dp.toPx())
            )
        }
    }
}
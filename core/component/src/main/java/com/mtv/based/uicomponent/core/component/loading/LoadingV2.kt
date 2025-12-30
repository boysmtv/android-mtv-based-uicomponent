package com.mtv.based.uicomponent.core.component.loading

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

@Composable
fun LoadingV2(
    modifier: Modifier = Modifier,
) {
    DoubleArcOppositeLoadingIndicator(
        modifier = modifier,
        color = AppTheme.colors.primary
    )
}

@Composable
fun DoubleArcOppositeLoadingIndicator(
    modifier: Modifier = Modifier,
    color: Color,
    diameter: Dp = 48.dp,
    strokeWidth: Dp = 3.dp,
    gap: Dp = 8.dp,
    durationMillis: Int = 1400
) {
    val infiniteTransition = rememberInfiniteTransition(label = "double_arc_opposite")

    val outerRotation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing
            )
        ),
        label = "outer_rotation"
    )

    val innerRotation = infiniteTransition.animateFloat(
        initialValue = 360f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis * 1.2f.toInt(),
                easing = LinearEasing
            )
        ),
        label = "inner_rotation"
    )

    Canvas(
        modifier = modifier.size(diameter)
    ) {
        val stroke = Stroke(
            width = strokeWidth.toPx(),
            cap = StrokeCap.Round
        )

        val gapPx = gap.toPx()

        drawArc(
            color = color,
            startAngle = outerRotation.value + 20f,
            sweepAngle = 240f,
            useCenter = false,
            style = stroke
        )

        drawArc(
            color = color.copy(alpha = 0.65f),
            startAngle = innerRotation.value,
            sweepAngle = 200f,
            useCenter = false,
            topLeft = Offset(gapPx, gapPx),
            size = Size(
                width = this.size.width - gapPx * 2,
                height = this.size.height - gapPx * 2
            ),
            style = stroke
        )
    }
}



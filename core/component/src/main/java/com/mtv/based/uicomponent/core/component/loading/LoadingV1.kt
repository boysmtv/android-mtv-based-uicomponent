package com.mtv.based.uicomponent.core.component.loading

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

@Composable
fun LoadingV1(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp
) {
    ArcLoadingIndicator(
        modifier = modifier,
        size = size,
        color = AppTheme.colors.primary
    )
}

@Composable
fun ArcLoadingIndicator(
    modifier: Modifier = Modifier,
    color: Color,
    strokeWidth: Dp = 4.dp,
    size: Dp = 48.dp,
    durationMillis: Int = 1200
) {
    val infiniteTransition = rememberInfiniteTransition(label = "arc_loader")

    val rotation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing
            )
        ),
        label = "rotation"
    )

    Canvas(
        modifier = modifier
            .size(size)
            .rotate(rotation.value)
    ) {
        val stroke = Stroke(
            width = strokeWidth.toPx(),
            cap = StrokeCap.Round
        )

        drawArc(
            color = color,
            startAngle = 30f,
            sweepAngle = 220f,
            useCenter = false,
            style = stroke
        )

        drawArc(
            color = color.copy(alpha = 0.8f),
            startAngle = 280f,
            sweepAngle = 60f,
            useCenter = false,
            style = stroke
        )
    }
}

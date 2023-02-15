package com.challenge.pixabay.presentation.common.loading.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier, indicatorSize: Dp = 32.dp, circleColors: List<Color> = listOf(
        Color(0xFF5851D8),
        Color(0xFF833AB4),
        Color(0xFFC13584),
        Color(0xFFE1306C),
        Color(0xFFFD1D1D),
        Color(0xFFF56040),
        Color(0xFFF77737),
        Color(0xFFFCAF45),
        Color(0xFFFFDC80),
        Color(0xFF5851D8)
    ), animationDuration: Int = 360
) {
    val infiniteTransition = rememberInfiniteTransition()

    val rotateAnimation by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration, easing = LinearEasing
            )
        )
    )

    CircularProgressIndicator(
        modifier = Modifier
            .size(size = indicatorSize)
            .rotate(degrees = rotateAnimation)
            .border(
                width = 4.dp, brush = Brush.sweepGradient(circleColors), shape = CircleShape
            ),
        progress = 1f,
        strokeWidth = 1.dp,
        color = MaterialTheme.colors.background // Set background color
    )
}

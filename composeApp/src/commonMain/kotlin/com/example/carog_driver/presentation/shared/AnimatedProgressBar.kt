package com.example.carog_driver.presentation.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.carog_driver.presentation.theme.AppTheme


@Composable
fun AnimatedProgressBar(
    modifier: Modifier = Modifier,
    barColor: Color = AppTheme.colors.onBackground.copy(alpha = 0.2f),
    progressColor: Color = AppTheme.colors.primary,
    progress: Float = 0f
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(AppTheme.dimens.md))
            .background(barColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(6.dp)
                .clip(RoundedCornerShape(AppTheme.dimens.md))
                .background(progressColor)
        )
    }
}
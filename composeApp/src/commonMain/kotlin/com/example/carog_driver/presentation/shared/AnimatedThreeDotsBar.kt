package com.example.carog_driver.presentation.shared

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import com.example.carog_driver.presentation.theme.AppTheme


@Composable
fun AnimatedThreeDotsBar(
    modifier: Modifier = Modifier,
    progress: Int
) {
    val animationDuration = 300

    val dot1Alpha by animateFloatAsState(
        targetValue = if (progress >= 1) 1f else 0f, animationSpec = tween(animationDuration), label = "dot1"
    )
    val div1Alpha by animateFloatAsState(
        targetValue = if (progress >= 2) 1f else 0f, animationSpec = tween(animationDuration), label = "div1"
    )
    val dot2Alpha by animateFloatAsState(
        targetValue = if (progress >= 3) 1f else 0f, animationSpec = tween(animationDuration), label = "dot2"
    )
    val div2Alpha by animateFloatAsState(
        targetValue = if (progress >= 4) 1f else 0f, animationSpec = tween(animationDuration), label = "div2"
    )
    val dot3Alpha by animateFloatAsState(
        targetValue = if (progress >= 5) 1f else 0f, animationSpec = tween(animationDuration), label = "dot3"
    )

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.sm),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .alpha(dot1Alpha)
                .size(AppTheme.dimens.base)
                .clip(AppTheme.shapes.pill)
                .background(AppTheme.colors.outline)
        )

        HorizontalDivider(
            modifier = Modifier.weight(1f).alpha(div1Alpha)
        )

        Box(
            modifier = Modifier
                .alpha(dot2Alpha)
                .size(AppTheme.dimens.base)
                .clip(AppTheme.shapes.pill)
                .background(AppTheme.colors.outline)
        )

        HorizontalDivider(
            modifier = Modifier.weight(1f).alpha(div2Alpha)
        )

        Box(
            modifier = Modifier
                .alpha(dot3Alpha)
                .size(AppTheme.dimens.base)
                .clip(AppTheme.shapes.pill)
                .background(AppTheme.colors.outline)
        )
    }
}

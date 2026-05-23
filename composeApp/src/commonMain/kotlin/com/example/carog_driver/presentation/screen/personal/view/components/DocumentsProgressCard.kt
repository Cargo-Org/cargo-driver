package com.example.carog_driver.presentation.screen.personal.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import carog_driver.composeapp.generated.resources.*
import com.example.carog_driver.presentation.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import kotlin.math.roundToInt

@Composable
fun DocumentsProgressCard(
    completedCount: Int,
    totalCount: Int,
    modifier: Modifier = Modifier
) {
    val safeTotal = totalCount.coerceAtLeast(1)
    val progress = (completedCount.toFloat() / safeTotal.toFloat())
        .coerceIn(0f, 1f)

    val percentage = (progress * 100).roundToInt()

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = AppTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surfaceContainerLow
        )
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.dimens.gutter),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.stackSm)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(Res.string.documents_progress),
                    color = AppTheme.colors.onSurface,
                    style = AppTheme.typography.labelMd
                )

                Text(
                    text = "$percentage%",
                    color = AppTheme.extraColors.brandBlue,
                    style = AppTheme.typography.labelMd
                )
            }

            Text(
                text = stringResource(
                    Res.string.documents_completed_count,
                    completedCount,
                    totalCount
                ),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.bodySmall
            )

            ProgressBar(progress = progress)
        }
    }
}

@Composable
private fun ProgressBar(
    progress: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(AppTheme.dimens.base)
            .clip(AppTheme.shapes.pill)
            .background(AppTheme.colors.surfaceContainerHigh)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(AppTheme.dimens.base)
                .clip(AppTheme.shapes.pill)
                .background(AppTheme.extraColors.brandBlue)
        )
    }
}

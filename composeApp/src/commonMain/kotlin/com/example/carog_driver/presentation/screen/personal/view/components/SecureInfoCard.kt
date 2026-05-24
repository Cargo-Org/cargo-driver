package com.example.carog_driver.presentation.screen.personal.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.carog_driver.presentation.theme.AppTheme
import androidx.compose.material3.Icon
import carog_driver.composeapp.generated.resources.Res
import carog_driver.composeapp.generated.resources.ic_lock
import carog_driver.composeapp.generated.resources.secure_upload_description
import carog_driver.composeapp.generated.resources.secure_upload_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SecureInfoCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = AppTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surfaceContainerLow
        )
    ) {
        Row(
            modifier = Modifier.padding(AppTheme.dimens.gutter),
            verticalAlignment = Alignment.Top
        ) {
            SecureIcon()

            Spacer(modifier = Modifier.width(AppTheme.dimens.sm))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(Res.string.secure_upload_title),
                    color = AppTheme.colors.onSurface,
                    style = AppTheme.typography.labelMd
                )

                Text(
                    text = stringResource(Res.string.secure_upload_description),
                    color = AppTheme.colors.onSurfaceVariant,
                    style = AppTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = AppTheme.dimens.xs)
                )
            }
        }
    }
}

@Composable
private fun SecureIcon() {
    Box(
        modifier = Modifier
            .size(AppTheme.dimens.lg)
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.extraColors.brandBlue.copy(alpha = 0.16f)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_lock),
            contentDescription = null,
            tint = AppTheme.extraColors.brandBlue,
            modifier = Modifier.size(AppTheme.dimens.md)
        )
    }
}

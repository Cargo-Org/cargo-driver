package com.example.carog_driver.presentation.vehicleregister.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.carog_driver.presentation.theme.AppTheme


@Composable
fun PageHeader(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    val colors = AppTheme.colors
    val typography = AppTheme.typography
    val dimens = AppTheme.dimens

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimens.xs)
    ) {
        Text(
            text = title,
            style = typography.headlineLg.copy(fontWeight = FontWeight.Bold),
            color = colors.onSurface,
        )

        Text(
            text = subtitle,
            style = typography.bodyStandard,
            color = colors.onSurfaceVariant,
        )
    }
}
package com.example.carog_driver.presentation.vehicleregister.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.carog_driver.presentation.theme.AppTheme


@Composable
fun RegistrationSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val colors     = AppTheme.colors
    val typography = AppTheme.typography
    val dimens     = AppTheme.dimens
    val shapes     = AppTheme.shapes

    Card(
        colors = CardDefaults.cardColors(
            containerColor = colors.surfaceContainerLow,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape     = shapes.large,
        modifier  = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimens.pageMargin,
                    vertical   = dimens.stackMd,
                ),
            verticalArrangement = Arrangement.spacedBy(dimens.gutter),
        ) {
            Text(
                text  = title,
                style = typography.sectionTitle,
                color = colors.onSurface,
            )

            content()
        }
    }
}
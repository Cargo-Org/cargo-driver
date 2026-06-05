package com.example.carog_driver.presentation.snackbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import carog_driver.composeapp.generated.resources.Res
import carog_driver.composeapp.generated.resources.undo
import com.example.carog_driver.presentation.theme.AppDimensions
import com.example.carog_driver.presentation.theme.AppTheme
import org.jetbrains.compose.resources.stringResource


private data class SnackbarColors(
    val container: Color,
    val text: Color
)

@Composable
private fun rememberSnackbarColors(type: SnackbarType): SnackbarColors {
    val colors = AppTheme.colors
    val extraColors = AppTheme.extraColors

    return remember(type) {
        when (type) {
            SnackbarType.SUCCESS -> SnackbarColors(
                container = extraColors.successGreenContainer,
                text = extraColors.successGreen
            )

            SnackbarType.ERROR -> SnackbarColors(
                container = colors.error,
                text = colors.onError
            )

            SnackbarType.WARNING -> SnackbarColors(
                container = extraColors.warningOrangeContainer,
                text = extraColors.warningOrange
            )

            SnackbarType.INFO -> SnackbarColors(
                container = colors.primary,
                text = colors.onPrimary
            )
        }
    }
}

@Composable
fun CargoSnackbar(
    snackbarData: SnackbarData
) {
    val config = snackbarData.visuals as CargoSnackbarConfig
    val dimensions = AppTheme.dimens
    val snackbarColors = rememberSnackbarColors(config.type)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = AppTheme.dimens.xxl)
            .padding(horizontal = dimensions.sm, vertical = dimensions.sm),
        color = snackbarColors.container,
        shape = RoundedCornerShape(AppTheme.dimens.base),
        shadowElevation = AppTheme.dimens.xs
    ) {
        CargoSnackbarContent(
            config = config,
            textColor = snackbarColors.text,
            dimensions = dimensions,
            onUndo = {
                snackbarData.dismiss()
                config.onUndo()
            }
        )
    }
}

@Composable
private fun CargoSnackbarContent(
    config: CargoSnackbarConfig,
    textColor: Color,
    dimensions: AppDimensions,
    onUndo: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensions.sm, vertical = dimensions.xs),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = config.message,
            color = textColor,
            style = AppTheme.typography.bodyMd,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        if (config.withUndoAction) {
            Spacer(modifier = Modifier.width(dimensions.sm))

            TextButton(
                onClick = onUndo,
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = stringResource(Res.string.undo).uppercase(),
                    color = textColor,
                    style = AppTheme.typography.buttonLabel.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}
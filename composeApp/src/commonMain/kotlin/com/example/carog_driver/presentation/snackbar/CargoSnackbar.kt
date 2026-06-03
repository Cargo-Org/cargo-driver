package com.example.carog_driver.presentation.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import carog_driver.composeapp.generated.resources.Dismiss
import carog_driver.composeapp.generated.resources.Res
import carog_driver.composeapp.generated.resources.ic_close
import com.example.carog_driver.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CargoSnackbar(
    snackbarData: SnackbarData
) {
    val config = snackbarData.visuals as CargoSnackbarConfig
    val colors = AppTheme.colors
    val dimensions = AppTheme.dimens

    val containerColor = when (config.type) {
        SnackbarType.SUCCESS -> AppTheme.extraColors.successGreenContainer
        SnackbarType.ERROR -> colors.error
        SnackbarType.WARNING -> AppTheme.extraColors.warningOrangeContainer
        SnackbarType.INFO -> colors.primary
    }

    val textColor = when (config.type) {
        SnackbarType.SUCCESS -> AppTheme.extraColors.successGreen
        SnackbarType.ERROR -> colors.onError
        SnackbarType.WARNING -> AppTheme.extraColors.warningOrange
        SnackbarType.INFO -> colors.onPrimary
    }

    val buttonBackgroundColor = textColor.copy(alpha = 0.12f)

    Snackbar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensions.sm + dimensions.xs, vertical = dimensions.sm),
        containerColor = containerColor,
        dismissAction = if (config.withDismissAction) {
            {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = dimensions.base, vertical = dimensions.base),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = {
                            snackbarData.dismiss()
                            config.onDismiss()
                        },
                        contentPadding = PaddingValues(horizontal = dimensions.base, vertical = dimensions.xs),
                        modifier = Modifier
                            .background(
                                color = buttonBackgroundColor,
                                shape = RoundedCornerShape(dimensions.base)
                            )
                    ) {
                        Text(
                            text = stringResource(Res.string.Dismiss),
                            color = textColor,
                            style = AppTheme.typography.labelMd,
                        )

                        Spacer(modifier = Modifier.width(dimensions.base))

                        Icon(
                            painter = painterResource(Res.drawable.ic_close),
                            contentDescription = "Close",
                            tint = textColor,
                        )
                    }
                }
            }
        } else null,
    ) {
        Text(
            text = config.message,
            color = textColor,
            style = AppTheme.typography.bodyMd
        )
    }
}


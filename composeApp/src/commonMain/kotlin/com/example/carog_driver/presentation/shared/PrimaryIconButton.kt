package com.example.carog_driver.presentation.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.carog_driver.presentation.theme.AppTheme

@Composable
fun PrimaryIconButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    icon: Painter? = null,
    iconSize: Dp = 20.dp,
    iconColor: Color? = null,
    isIconLeading: Boolean = false,
    shape: Shape = AppTheme.shapes.medium,
    backgroundColor: Color = AppTheme.colors.primary,
    foregroundColor: Color = AppTheme.colors.onPrimary,
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = foregroundColor,
            disabledContainerColor = AppTheme.colors.primary.copy(alpha = 0.4f),
            disabledContentColor = AppTheme.colors.onPrimary.copy(alpha = 0.6f),
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = AppTheme.dimens.base),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (isIconLeading && icon != null) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    painter = icon,
                    contentDescription = null,
                    tint = iconColor ?: AppTheme.colors.onPrimary
                )

                Spacer(Modifier.width(AppTheme.dimens.sm))
            }

            Text(
                text = text,
                style = AppTheme.typography.buttonLabel,
            )

            if (!isIconLeading && icon != null) {
                Spacer(Modifier.width(AppTheme.dimens.sm))

                Icon(
                    modifier = Modifier.size(iconSize),
                    painter = icon,
                    contentDescription = null,
                    tint = iconColor ?: AppTheme.colors.onPrimary
                )
            }
        }
    }
}

package com.example.carog_driver.presentation.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.carog_driver.presentation.theme.AppTheme

@Composable
fun InputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    leadingIcon: Painter? = null,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visibilityOnPainter: Painter? = null,
    visibilityOffPainter: Painter? = null,
    iconTint: Color? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val colors = AppTheme.colors
    val typography = AppTheme.typography
    val dimens = AppTheme.dimens
    val finalIconTint = iconTint ?: colors.onSurfaceVariant
    val isError = errorMessage != null

    Column(modifier = modifier.fillMaxWidth()) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                style = typography.labelMuted,
                color = colors.onSurfaceVariant,
                modifier = Modifier.padding(bottom = dimens.xs),
            )
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    style = typography.bodyMd,
                    color = colors.onSurfaceVariant.copy(alpha = 0.5f),
                )
            },
            isError = isError,
            supportingText = errorMessage?.let { errorText ->
                {
                    Text(
                        text = errorText,
                        color = colors.error,
                        modifier = Modifier.padding(top = dimens.xs)
                    )
                }
            },
            leadingIcon = leadingIcon?.let { icon ->
                {
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        tint = finalIconTint,
                        modifier = Modifier.size(dimens.md),
                    )
                }
            },
            trailingIcon = takeIf { isPassword && visibilityOnPainter != null && visibilityOffPainter != null }?.let {
                {
                    val painter = if (passwordVisible) visibilityOnPainter!! else visibilityOffPainter!!

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painter,
                            contentDescription = null,
                            tint = finalIconTint,
                            modifier = Modifier.size(dimens.md),
                        )
                    }
                }
            },
            visualTransformation = if (isPassword && !passwordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = keyboardActions,
            singleLine = true,
            shape = AppTheme.shapes.small,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = colors.surfaceVariant.copy(alpha = 0.3f),
                focusedContainerColor = colors.surfaceVariant.copy(alpha = 0.3f),
                unfocusedBorderColor = colors.outline.copy(alpha = 0.4f),
                focusedBorderColor = colors.primary,
                errorBorderColor = colors.errorContainer,
                errorCursorColor = colors.onError,
            ),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
package com.example.carog_driver.presentation.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carog_driver.presentation.theme.AppTheme
import com.example.carog_driver.presentation.theme.CargoTheme

@Composable
fun InputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIcon: Painter? = null,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    visibilityOnPainter: Painter? = null,
    visibilityOffPainter: Painter? = null,
    iconTint: Color? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val colors = AppTheme.colors
    val typography = AppTheme.typography
    val dimens = AppTheme.dimens
    val finalIconTint = iconTint ?: colors.onSurfaceVariant

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
            leadingIcon = if (leadingIcon != null) {
                {
                    Icon(
                        painter = leadingIcon,
                        contentDescription = null,
                        tint = finalIconTint,
                        modifier = Modifier.size(18.dp),
                    )
                }
            } else null,
            trailingIcon = if (isPassword && visibilityOnPainter != null && visibilityOffPainter != null) {
                {
                    val painter = if (passwordVisible) visibilityOnPainter else visibilityOffPainter

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painter,
                            contentDescription = null,
                            tint = finalIconTint,
                            modifier = Modifier.size(18.dp),
                        )
                    }
                }
            } else null,
            visualTransformation = if (isPassword && !passwordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = colors.surfaceVariant.copy(alpha = 0.3f),
                focusedContainerColor = colors.surfaceVariant.copy(alpha = 0.3f),
                unfocusedBorderColor = colors.outline.copy(alpha = 0.4f),
                focusedBorderColor = colors.primary,
            ),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF101419, name = "InputField — Dark")
@Composable
private fun InputFieldDarkPreview() {
    CargoTheme(darkTheme = true) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            InputField(
                label = "Email Address",
                value = "",
                onValueChange = {},
                placeholder = "driver@cargo.com",
            )
        }
    }
}
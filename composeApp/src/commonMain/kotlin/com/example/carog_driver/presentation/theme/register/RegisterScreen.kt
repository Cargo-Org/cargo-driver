package com.example.carog_driver.presentation.theme.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import carog_driver.composeapp.generated.resources.Res
import carog_driver.composeapp.generated.resources.ic_arrow_right
import carog_driver.composeapp.generated.resources.ic_email
import carog_driver.composeapp.generated.resources.ic_eye_off
import carog_driver.composeapp.generated.resources.ic_eye_on
import carog_driver.composeapp.generated.resources.ic_google
import carog_driver.composeapp.generated.resources.ic_password
import carog_driver.composeapp.generated.resources.ic_person
import carog_driver.composeapp.generated.resources.ic_phone
import com.example.carog_driver.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun TransparentInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIcon: Painter? = null,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    visibilityOnPainter: Painter? = null,
    visibilityOffPainter: Painter? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val colors = AppTheme.colors
    val typography = AppTheme.typography
    val shapes = AppTheme.shapes
    val dimens = AppTheme.dimens

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimens.xs),
    ) {
        Text(
            text = label,
            style = typography.labelMd.copy(
                color = colors.onSurfaceVariant,
                fontWeight = FontWeight.Normal,
            ),
            modifier = Modifier.padding(bottom = dimens.xs),
        )

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
                        tint = colors.onSurfaceVariant.copy(alpha = 0.5f),
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
                            tint = colors.onSurfaceVariant.copy(alpha = 0.5f),
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
            shape = shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedBorderColor = colors.outline.copy(alpha = 0.4f),
                focusedBorderColor = colors.primary,
            ),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}


@Composable
fun PrimaryIconButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    icon: Painter? = null,
    iconColor: Color? = null
) {
    val colors = AppTheme.colors
    val typography = AppTheme.typography
    val shapes = AppTheme.shapes
    val dimens = AppTheme.dimens

    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        enabled = enabled,
        shape = shapes.pill,
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.primary,
            contentColor = colors.onPrimary,
            disabledContainerColor = colors.primary.copy(alpha = 0.4f),
            disabledContentColor = colors.onPrimary.copy(alpha = 0.6f),
        ),
    ) {

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = dimens.base),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = text,
                style = typography.buttonLabel,
            )

            Box(modifier = Modifier.width(12.dp))

            if (icon != null) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = iconColor ?: colors.onSurfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.size(18.dp),
                )
            }
        }

    }
}

@Composable
fun SocialButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: Painter,
    onClick: () -> Unit,
) {
    val colors = AppTheme.colors
    val typography = AppTheme.typography
    val shapes = AppTheme.shapes
    val dimens = AppTheme.dimens

    OutlinedButton(
        onClick = onClick,
        shape = shapes.medium,
        border = BorderStroke(1.dp, colors.outline.copy(alpha = 0.4f)),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = colors.surface,
            contentColor = colors.onSurface,
        ),
        modifier = Modifier.fillMaxWidth().padding(vertical = dimens.base),
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(20.dp),
        )
        Spacer(modifier = Modifier.width(dimens.sm - dimens.xs))
        Text(
            text = text,
            style = typography.labelMd,
        )
    }
}


@Composable
fun TextLink(
    prefixText: String,
    linkText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = AppTheme.colors
    val typography = AppTheme.typography

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = prefixText,
            style = typography.bodySmall,
            color = colors.onSurfaceVariant.copy(alpha = 0.7f),
        )
        TextButton(
            onClick = onClick,
            contentPadding = PaddingValues(horizontal = 2.dp, vertical = 0.dp),
        ) {
            Text(
                text = linkText,
                style = typography.labelMuted,
                color = colors.primary,
            )
        }
    }
}


@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    var fullNameValue by remember { mutableStateOf("") }
    var phoneNumberValue by remember { mutableStateOf("") }
    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    var confirmPasswordValue by remember { mutableStateOf("") }

    Box(
        modifier = modifier.fillMaxSize()
            .background(AppTheme.colors.surfaceVariant)
            .padding(vertical = 60.dp, horizontal = 24.dp),
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
                .border(
                    shape = RoundedCornerShape(size = 20.dp),
                    border = BorderStroke(width = 0.dp, color = AppTheme.colors.surfaceVariant)
                )
                .shadow(elevation = 2.dp, shape = RoundedCornerShape(size = 20.dp))
                .background(AppTheme.colors.background),
        ) {

            Column(
                modifier = Modifier.matchParentSize()
                    .padding(horizontal = 20.dp, vertical = 24.dp)
                    .scrollable(scrollState, orientation = Orientation.Vertical),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.md),
                horizontalAlignment = Alignment.Start
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.sm)
                ) {
                    Text(
                        "Create Account",
                        style = AppTheme.typography.headlineLg.copy(
                            color = AppTheme.colors.primary
                        )
                    )

                    Text(
                        "Fill in your details to start driving with Logistics Pro.",
                        style = AppTheme.typography.bodyMd
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.sm)
                ) {

                    TransparentInputField(
                        label = "Full Name",
                        value = fullNameValue,
                        onValueChange = { newValue -> fullNameValue = newValue },
                        placeholder = "Ahmed Mohamed",
                        isPassword = false,
                        keyboardType = KeyboardType.Text,
                        leadingIcon = painterResource(Res.drawable.ic_person),
                    )

                    TransparentInputField(
                        label = "Phone Number",
                        value = phoneNumberValue,
                        onValueChange = { newValue -> phoneNumberValue = newValue },
                        placeholder = "+(20) 111 111 1111",
                        isPassword = false,
                        keyboardType = KeyboardType.Phone,
                        leadingIcon = painterResource(Res.drawable.ic_phone),
                    )

                    TransparentInputField(
                        label = "Email Address",
                        value = emailValue,
                        onValueChange = { newValue -> emailValue = newValue },
                        placeholder = "ahmed@example.com",
                        isPassword = false,
                        keyboardType = KeyboardType.Email,
                        leadingIcon = painterResource(Res.drawable.ic_email),
                    )

                    TransparentInputField(
                        label = "Password",
                        value = passwordValue,
                        onValueChange = { newValue -> passwordValue = newValue },
                        placeholder = "•••••••••",
                        isPassword = true,
                        keyboardType = KeyboardType.Password,
                        leadingIcon = painterResource(Res.drawable.ic_password),
                        visibilityOnPainter = painterResource(Res.drawable.ic_eye_on),
                        visibilityOffPainter = painterResource(Res.drawable.ic_eye_off),
                    )

                    TransparentInputField(
                        label = "Confirm Password",
                        value = confirmPasswordValue,
                        onValueChange = { newValue -> confirmPasswordValue = newValue },
                        placeholder = "•••••••••",
                        isPassword = true,
                        keyboardType = KeyboardType.Password,
                        leadingIcon = painterResource(Res.drawable.ic_password),
                        visibilityOnPainter = painterResource(Res.drawable.ic_eye_on),
                        visibilityOffPainter = painterResource(Res.drawable.ic_eye_off),
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.md)
                ) {

                    PrimaryIconButton(
                        text = "Create Account",
                        icon = painterResource(Res.drawable.ic_arrow_right),
                        iconColor = AppTheme.colors.onPrimary,
                        onClick = {
                            //TODO: Create account
                        }
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HorizontalDivider(modifier = Modifier.weight(1.0f), color = AppTheme.colors.outline)

                        Text("   OR   ", style = AppTheme.typography.bodyMd.copy(
                            color = AppTheme.colors.outline
                        ))

                        HorizontalDivider(modifier = Modifier.weight(1.0f), color = AppTheme.colors.outline)
                    }

                    SocialButton(
                        text = "Continue With Google",
                        icon = painterResource(Res.drawable.ic_google),
                        onClick = {
                            //TODO: Continue With Google
                        }
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Already have an account? ", style = AppTheme.typography.bodyMd)

                        Text("Log In", style = AppTheme.typography.bodyMd.copy(
                            color = AppTheme.colors.primary
                        ))
                    }
                }
            }

        }

    }
}
package com.example.carog_driver.presentation.auth.login.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import carog_driver.composeapp.generated.resources.*
import com.example.carog_driver.presentation.shared.InputField
import com.example.carog_driver.presentation.shared.PrimaryButton
import com.example.carog_driver.presentation.shared.SocialButton
import com.example.carog_driver.presentation.shared.TextLink
import com.example.carog_driver.presentation.theme.AppTheme
import com.example.carog_driver.presentation.theme.CargoTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreen(
    onForgotPassword: () -> Unit = {},
    onSignIn: (email: String, password: String) -> Unit = { _, _ -> },
    onGoogleSignIn: () -> Unit = {},
    onAppleSignIn: () -> Unit = {},
    onApplyToDrive: () -> Unit = {},
) {
    LoginScreenContent(
        onForgotPassword = onForgotPassword,
        onSignIn = onSignIn,
        onGoogleSignIn = onGoogleSignIn,
        onAppleSignIn = onAppleSignIn,
        onApplyToDrive = onApplyToDrive,
    )
}

@Composable
private fun LoginScreenContent(
    modifier: Modifier = Modifier,
    onForgotPassword: () -> Unit = {},
    onSignIn: (email: String, password: String) -> Unit = { _, _ -> },
    onGoogleSignIn: () -> Unit = {},
    onAppleSignIn: () -> Unit = {},
    onApplyToDrive: () -> Unit = {},
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val colors = AppTheme.colors
    val typography = AppTheme.typography
    val dimens = AppTheme.dimens

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background),
        contentAlignment = Alignment.BottomCenter,
    ) {

        Card(
            colors = CardDefaults.cardColors(containerColor = colors.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = dimens.stackLg),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(modifier = Modifier.height(dimens.md))

                Text(
                    text = stringResource(Res.string.app_name),
                    style = typography.sectionTitle,
                    color = colors.primary,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(dimens.sm))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimens.pageMargin)
                ) {

                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colors.surfaceContainerLow
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = dimens.md, horizontal = dimens.gutter),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(dimens.xs),
                        ) {
                            Text(
                                text = stringResource(Res.string.welcome_back),
                                style = typography.sectionTitle,
                                color = colors.primary,
                                textAlign = TextAlign.Center,
                            )
                            Text(
                                text = stringResource(Res.string.login_subtitle),
                                style = typography.bodyStandard,
                                color = colors.onSurfaceVariant,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(dimens.stackMd))

                    Text(
                        text = stringResource(Res.string.email_address),
                        style = typography.labelMuted,
                        color = colors.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = dimens.xs)
                    )
                    InputField(
                        label = "",
                        value = email,
                        onValueChange = { email = it },
                        placeholder = stringResource(Res.string.email_placeholder),
                        leadingIcon = painterResource(Res.drawable.ic_email),
                        iconTint = colors.onSurfaceVariant,
                        keyboardType = KeyboardType.Email,
                    )

                    Spacer(modifier = Modifier.height(dimens.gutter))

                    Text(
                        text = stringResource(Res.string.password),
                        style = typography.labelMuted,
                        color = colors.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = dimens.xs)
                    )

                    InputField(
                        label = "",
                        value = password,
                        onValueChange = { password = it },
                        placeholder = stringResource(Res.string.password_placeholder),
                        leadingIcon = painterResource(Res.drawable.ic_lock),
                        isPassword = true,
                        visibilityOnPainter = painterResource(Res.drawable.ic_visibility),
                        visibilityOffPainter = painterResource(Res.drawable.ic_visibility_off),
                        iconTint = colors.onSurfaceVariant,
                        keyboardType = KeyboardType.Password,
                    )

                    TextButton(
                        onClick = onForgotPassword,
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = dimens.xs)
                            .height(24.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = stringResource(Res.string.forgot_password),
                            style = typography.labelMd,
                            color = colors.secondary,
                        )
                    }

                    Spacer(modifier = Modifier.height(dimens.stackMd))

                    PrimaryButton(
                        text = stringResource(Res.string.sign_in),
                        onClick = { onSignIn(email, password) },
                    )

                    OrDivider(
                        label = stringResource(Res.string.or_continue_with),
                        modifier = Modifier.padding(vertical = dimens.stackMd),
                    )

                    SocialButton(
                        text = stringResource(Res.string.continue_with_google),
                        icon = painterResource(Res.drawable.ic_google),
                        iconTint = Color.Unspecified,
                        onClick = onGoogleSignIn,
                    )

                    Spacer(modifier = Modifier.height(dimens.sm))

                    SocialButton(
                        text = stringResource(Res.string.continue_with_apple),
                        icon = painterResource(Res.drawable.ic_apple),
                        iconTint = colors.onSurface,
                        onClick = onAppleSignIn,
                    )

                    Spacer(modifier = Modifier.height(dimens.stackLg))

                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        TextLink(
                            prefixText = stringResource(Res.string.dont_have_account),
                            linkText = stringResource(Res.string.apply_to_drive),
                            onClick = onApplyToDrive,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun OrDivider(
    label: String,
    modifier: Modifier = Modifier,
) {
    val colors = AppTheme.colors

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gutter),
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = colors.outlineVariant,
        )
        Text(
            text = label,
            style = AppTheme.typography.labelSm,
            color = colors.outline,
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = colors.outlineVariant,
        )
    }
}


package com.example.carog_driver.presentation.screen.register.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import carog_driver.composeapp.generated.resources.Res
import carog_driver.composeapp.generated.resources.already_have_an_account
import carog_driver.composeapp.generated.resources.confirm_password
import carog_driver.composeapp.generated.resources.continue_with_google
import carog_driver.composeapp.generated.resources.create_account
import carog_driver.composeapp.generated.resources.email_address
import carog_driver.composeapp.generated.resources.fill_your_details_title
import carog_driver.composeapp.generated.resources.full_name
import carog_driver.composeapp.generated.resources.ic_arrow
import carog_driver.composeapp.generated.resources.ic_email
import carog_driver.composeapp.generated.resources.ic_eye_off
import carog_driver.composeapp.generated.resources.ic_eye_on
import carog_driver.composeapp.generated.resources.ic_google
import carog_driver.composeapp.generated.resources.ic_password
import carog_driver.composeapp.generated.resources.ic_person
import carog_driver.composeapp.generated.resources.ic_phone
import carog_driver.composeapp.generated.resources.login
import carog_driver.composeapp.generated.resources.or
import carog_driver.composeapp.generated.resources.password
import carog_driver.composeapp.generated.resources.phone_number
import carog_driver.composeapp.generated.resources.sample_name
import carog_driver.composeapp.generated.resources.sample_phone
import com.example.carog_driver.presentation.screen.register.uimodel.RegisterFormError
import com.example.carog_driver.presentation.screen.register.viewmodel.RegisterInteraction
import com.example.carog_driver.presentation.screen.register.viewmodel.RegisterUiState
import com.example.carog_driver.presentation.shared.InputField
import com.example.carog_driver.presentation.shared.PrimaryIconButton
import com.example.carog_driver.presentation.shared.SocialButton
import com.example.carog_driver.presentation.shared.TextLink
import com.example.carog_driver.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun RegisterFormContent(
    modifier: Modifier = Modifier,
    interaction: RegisterInteraction,
    state: RegisterUiState,
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier.fillMaxSize()
            .padding(vertical = 60.dp, horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier.matchParentSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.sm),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                stringResource(Res.string.create_account),
                style = AppTheme.typography.headlineLg.copy(
                    color = AppTheme.colors.onBackground
                )
            )

            Text(
                stringResource(Res.string.fill_your_details_title),
                style = AppTheme.typography.bodyMd.copy(
                    fontWeight = FontWeight.Normal,
                    color = AppTheme.colors.onBackground
                )
            )

            Box(modifier = Modifier.width(AppTheme.dimens.sm))

            InputField(
                label = stringResource(Res.string.full_name),
                value = state.form.fullName,
                onValueChange = { newValue -> interaction.nameChanged(newValue) },
                placeholder = stringResource(Res.string.sample_name),
                isPassword = false,
                keyboardType = KeyboardType.Text,
                leadingIcon = painterResource(Res.drawable.ic_person),
                errorMessage =
                    if (state.registerFormError is RegisterFormError.NameFormError
                        && state.error != null
                    ) stringResource(state.error)
                    else null,
            )

            InputField(
                label = stringResource(Res.string.phone_number),
                value = state.form.phoneNumber,
                onValueChange = { newValue -> interaction.phoneChanged(newValue) },
                placeholder = stringResource(Res.string.sample_phone),
                isPassword = false,
                keyboardType = KeyboardType.Phone,
                leadingIcon = painterResource(Res.drawable.ic_phone),
                errorMessage =
                    if (state.registerFormError is RegisterFormError.PhoneFormError
                        && state.error != null
                    ) stringResource(state.error)
                    else null,
            )

            InputField(
                label = stringResource(Res.string.email_address),
                value = state.form.email,
                onValueChange = { newValue -> interaction.emailChanged(newValue) },
                placeholder = "ahmed@example.com",
                isPassword = false,
                keyboardType = KeyboardType.Email,
                leadingIcon = painterResource(Res.drawable.ic_email),
                errorMessage =
                    if (state.registerFormError is RegisterFormError.EmailFormError
                        && state.error != null
                    ) stringResource(state.error)
                    else null,
            )

            InputField(
                label = stringResource(Res.string.password),
                value = state.form.password,
                onValueChange = { newValue -> interaction.passwordChanged(newValue) },
                placeholder = "•••••••••",
                isPassword = true,
                keyboardType = KeyboardType.Password,
                leadingIcon = painterResource(Res.drawable.ic_password),
                visibilityOnPainter = painterResource(Res.drawable.ic_eye_on),
                visibilityOffPainter = painterResource(Res.drawable.ic_eye_off),
                errorMessage =
                    if (state.registerFormError is RegisterFormError.PasswordFormError
                        && state.error != null
                    ) stringResource(state.error)
                    else null,
            )

            InputField(
                label = stringResource(Res.string.confirm_password),
                value = state.form.confirmPassword,
                onValueChange = { newValue -> interaction.confirmPasswordChanged(newValue) },
                placeholder = "•••••••••",
                isPassword = true,
                keyboardType = KeyboardType.Password,
                leadingIcon = painterResource(Res.drawable.ic_password),
                visibilityOnPainter = painterResource(Res.drawable.ic_eye_on),
                visibilityOffPainter = painterResource(Res.drawable.ic_eye_off),
                errorMessage =
                    if (state.registerFormError is RegisterFormError.ConfirmPasswordFormError
                        && state.error != null
                    ) stringResource(state.error)
                    else null,
            )

            Box(modifier = Modifier.height(AppTheme.dimens.md))

            PrimaryIconButton(
                text = stringResource(Res.string.create_account),
                icon = painterResource(Res.drawable.ic_arrow),
                iconColor = AppTheme.colors.onPrimary,
                onClick = {
                    interaction.register()
                }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1.0f),
                    color = AppTheme.colors.outline
                )

                Text(
                    "   " + stringResource(Res.string.or) + "   ",
                    style = AppTheme.typography.bodyMd.copy(
                        color = AppTheme.colors.outline
                    )
                )

                HorizontalDivider(
                    modifier = Modifier.weight(1.0f),
                    color = AppTheme.colors.outline
                )
            }

            SocialButton(
                text = stringResource(Res.string.continue_with_google),
                icon = painterResource(Res.drawable.ic_google),
                onClick = {
                    interaction.registerWithGoogle()
                }
            )

            TextLink(
                prefixText = stringResource(Res.string.already_have_an_account),
                linkText = stringResource(Res.string.login),
                onClick = {
                    interaction.navigateToLoginScreen()
                },
            )
        }
    }
}
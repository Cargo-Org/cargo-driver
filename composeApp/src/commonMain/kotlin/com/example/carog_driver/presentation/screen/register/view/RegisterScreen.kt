package com.example.carog_driver.presentation.screen.register.view

import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.carog_driver.presentation.shared.InputField
import com.example.carog_driver.presentation.shared.PrimaryIconButton
import com.example.carog_driver.presentation.shared.SocialButton
import com.example.carog_driver.presentation.shared.TextLink
import com.example.carog_driver.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier
){
    RegisterScreenContent(
        modifier = modifier
    )
}


@Composable
fun RegisterScreenContent(
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
            .background(AppTheme.colors.background)
            .padding(vertical = 60.dp, horizontal = 24.dp),
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
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
                    value = fullNameValue,
                    onValueChange = { newValue -> fullNameValue = newValue },
                    placeholder = stringResource(Res.string.sample_name),
                    isPassword = false,
                    keyboardType = KeyboardType.Text,
                    leadingIcon = painterResource(Res.drawable.ic_person),
                )

                InputField(
                    label = stringResource(Res.string.phone_number),
                    value = phoneNumberValue,
                    onValueChange = { newValue -> phoneNumberValue = newValue },
                    placeholder = stringResource(Res.string.sample_phone),
                    isPassword = false,
                    keyboardType = KeyboardType.Phone,
                    leadingIcon = painterResource(Res.drawable.ic_phone),
                )

                InputField(
                    label = stringResource(Res.string.email_address),
                    value = emailValue,
                    onValueChange = { newValue -> emailValue = newValue },
                    placeholder = "ahmed@example.com",
                    isPassword = false,
                    keyboardType = KeyboardType.Email,
                    leadingIcon = painterResource(Res.drawable.ic_email),
                )

                InputField(
                    label = stringResource(Res.string.password),
                    value = passwordValue,
                    onValueChange = { newValue -> passwordValue = newValue },
                    placeholder = "•••••••••",
                    isPassword = true,
                    keyboardType = KeyboardType.Password,
                    leadingIcon = painterResource(Res.drawable.ic_password),
                    visibilityOnPainter = painterResource(Res.drawable.ic_eye_on),
                    visibilityOffPainter = painterResource(Res.drawable.ic_eye_off),
                )

                InputField(
                    label = stringResource(Res.string.confirm_password),
                    value = confirmPasswordValue,
                    onValueChange = { newValue -> confirmPasswordValue = newValue },
                    placeholder = "•••••••••",
                    isPassword = true,
                    keyboardType = KeyboardType.Password,
                    leadingIcon = painterResource(Res.drawable.ic_password),
                    visibilityOnPainter = painterResource(Res.drawable.ic_eye_on),
                    visibilityOffPainter = painterResource(Res.drawable.ic_eye_off),
                )

                Box(modifier = Modifier.height(AppTheme.dimens.md))

                PrimaryIconButton(
                    text = stringResource(Res.string.create_account),
                    icon = painterResource(Res.drawable.ic_arrow),
                    iconColor = AppTheme.colors.onPrimary,
                    onClick = {
                        //TODO: Create account
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
                        //TODO: Continue With Google
                    }
                )

                TextLink(
                    prefixText = stringResource(Res.string.already_have_an_account),
                    linkText = stringResource(Res.string.login),
                    onClick = {
                        //TODO: Navigate to login screen
                    },
                )
            }
        }
    }
}
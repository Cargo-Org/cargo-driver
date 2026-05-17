package com.example.carog_driver.presentation.completeregister.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import carog_driver.composeapp.generated.resources.complete_your_profile
import carog_driver.composeapp.generated.resources.email_address
import carog_driver.composeapp.generated.resources.fill_your_details_title
import carog_driver.composeapp.generated.resources.full_name
import carog_driver.composeapp.generated.resources.ic_arrow
import carog_driver.composeapp.generated.resources.ic_email
import carog_driver.composeapp.generated.resources.ic_person
import carog_driver.composeapp.generated.resources.ic_phone
import carog_driver.composeapp.generated.resources.phone_number
import carog_driver.composeapp.generated.resources.sample_name
import carog_driver.composeapp.generated.resources.sample_phone
import carog_driver.composeapp.generated.resources.send_otp
import com.example.carog_driver.presentation.shared.DisabledInputField
import com.example.carog_driver.presentation.shared.InputField
import com.example.carog_driver.presentation.shared.PrimaryIconButton
import com.example.carog_driver.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun CompleteRegister(
    modifier: Modifier = Modifier
){
    var fullNameValue by remember { mutableStateOf("") }
    var phoneNumberValue by remember { mutableStateOf("") }
    val emailValue = "googleEmail@google.com"

    Box(
        modifier = modifier.fillMaxSize()
            .background(AppTheme.colors.background)
            .padding(vertical = 60.dp, horizontal = 24.dp),
    ) {

        Column(
            modifier = Modifier.matchParentSize(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.md),
            horizontalAlignment = Alignment.Start
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.sm)
            ) {
                Text(
                    stringResource(Res.string.complete_your_profile),
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
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.sm)
            ) {

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

                DisabledInputField(
                    label = stringResource(Res.string.email_address),
                    value = emailValue,
                    isPassword = false,
                    keyboardType = KeyboardType.Email,
                    leadingIcon = painterResource(Res.drawable.ic_email),
                )
            }

            Box(modifier = Modifier.height(AppTheme.dimens.sm))

            PrimaryIconButton(
                text = stringResource(Res.string.send_otp),
                icon = painterResource(Res.drawable.ic_arrow),
                iconColor = AppTheme.colors.onPrimary,
                onClick = {
                    //TODO: Send OTP
                }
            )
        }
    }
}
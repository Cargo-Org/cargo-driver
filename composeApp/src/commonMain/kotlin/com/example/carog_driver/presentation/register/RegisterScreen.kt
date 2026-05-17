package com.example.carog_driver.presentation.register
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import carog_driver.composeapp.generated.resources.ic_arrow_right
import carog_driver.composeapp.generated.resources.ic_email
import carog_driver.composeapp.generated.resources.ic_eye_off
import carog_driver.composeapp.generated.resources.ic_eye_on
import carog_driver.composeapp.generated.resources.ic_google
import carog_driver.composeapp.generated.resources.ic_password
import carog_driver.composeapp.generated.resources.ic_person
import carog_driver.composeapp.generated.resources.ic_phone
import com.example.carog_driver.presentation.shared.InputField
import com.example.carog_driver.presentation.shared.PrimaryIconButton
import com.example.carog_driver.presentation.shared.SocialButton
import com.example.carog_driver.presentation.shared.TextLink
import com.example.carog_driver.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource


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
            .background(AppTheme.colors.background)
            .padding(vertical = 60.dp, horizontal = 24.dp),
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.matchParentSize()
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.md),
                horizontalAlignment = Alignment.Start
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.sm)
                ) {
                    Text(
                        "Create Account",
                        style = AppTheme.typography.headlineLg.copy(
                            color = AppTheme.colors.onBackground
                        )
                    )

                    Text(
                        "Fill in your details to start driving with Logistics Pro.",
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
                        label = "Full Name",
                        value = fullNameValue,
                        onValueChange = { newValue -> fullNameValue = newValue },
                        placeholder = "Ahmed Mohamed",
                        isPassword = false,
                        keyboardType = KeyboardType.Text,
                        leadingIcon = painterResource(Res.drawable.ic_person),
                    )

                    InputField(
                        label = "Phone Number",
                        value = phoneNumberValue,
                        onValueChange = { newValue -> phoneNumberValue = newValue },
                        placeholder = "+(20) 111 111 1111",
                        isPassword = false,
                        keyboardType = KeyboardType.Phone,
                        leadingIcon = painterResource(Res.drawable.ic_phone),
                    )

                    InputField(
                        label = "Email Address",
                        value = emailValue,
                        onValueChange = { newValue -> emailValue = newValue },
                        placeholder = "ahmed@example.com",
                        isPassword = false,
                        keyboardType = KeyboardType.Email,
                        leadingIcon = painterResource(Res.drawable.ic_email),
                    )

                    InputField(
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

                    InputField(
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

                Box(modifier = Modifier.height(AppTheme.dimens.sm))

                Column(
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.sm)
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
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextLink(
                            prefixText = "Already have an account?",
                            linkText = "Log In",
                            onClick = {
                                //TODO: Navigate to login screen
                            },
                        )
                    }
                }
            }
        }
    }
}
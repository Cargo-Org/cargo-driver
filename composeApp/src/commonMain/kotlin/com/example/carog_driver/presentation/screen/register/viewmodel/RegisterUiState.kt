package com.example.carog_driver.presentation.screen.register.viewmodel

import com.example.carog_driver.presentation.screen.register.uimodel.RegistrationForm
import com.example.carog_driver.presentation.screen.register.uimodel.RegisterFormError
import org.jetbrains.compose.resources.StringResource

data class RegisterUiState(
    val form: RegistrationForm = RegistrationForm(),
    val isLoading: Boolean = false,
    val error: StringResource? = null,
    val registerFormError: RegisterFormError? = null,
    val isSuccess: Boolean = false
)
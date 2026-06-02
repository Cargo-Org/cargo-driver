package com.example.carog_driver.presentation.screen.register.viewmodel

import carog_driver.composeapp.generated.resources.Res
import carog_driver.composeapp.generated.resources.verify_your_id
import com.cargo.driver.shared.domain.model.register.RegisterError
import com.cargo.driver.shared.domain.model.register.RegisterSuccess
import com.cargo.driver.shared.domain.usecase.register.RegisterUseCase
import com.example.carog_driver.presentation.base.BaseViewModel
import com.example.carog_driver.presentation.screen.register.mapper.toModel
import com.example.carog_driver.presentation.screen.register.mapper.toRegisterFormError
import com.example.carog_driver.presentation.screen.register.mapper.toStringResource
import com.example.carog_driver.presentation.screen.register.uimodel.RegisterFormError

class RegisterViewModel(
    val registerUseCase: RegisterUseCase,
) : BaseViewModel<RegisterUiState, RegisterEffect>(RegisterUiState()),
    RegisterInteraction {

    private val emailRegex =
        Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

    private val phoneRegex =
        Regex("^01[0125][0-9]{8}$")

    override fun register() {
        val error = validateRegisterForm()
        if (error != null) {
            println("Validation Error + $error")
            updateState { this.copy(registerFormError = error, error = error.toStringResource()) }
            return
        }

        tryToExecute(
            block = {
                registerUseCase(state.value.form.toModel())
            },
            onStart = {
                updateState { this.copy(isLoading = true) }
            },
            onError = {
                updateState {
                    this.copy(
                        error = Res.string.verify_your_id,
                        registerFormError = RegisterFormError.Unknown
                    )
                }
            },
            onSuccess = { response ->
                when (response) {
                    RegisterSuccess -> {
                        updateState {
                            copy(isSuccess = true, error = null, registerFormError = null)
                        }
                        sendEffect(RegisterEffect.NavigateToOtpScreen)
                    }

                    is RegisterError -> {
                        when (val formError = response.toRegisterFormError()) {
                            RegisterFormError.NoInternet -> TODO()
                            RegisterFormError.Unknown -> TODO()
                            else -> {
                                updateState {
                                    copy(
                                        error = formError.toStringResource(),
                                        registerFormError = formError
                                    )
                                }
                            }
                        }
                    }
                }
            },
            onEnd = {
                updateState {
                    copy(isLoading = false)
                }
            },
        )
    }

    override fun registerWithGoogle() {
        TODO("Not yet implemented")
    }

    override fun navigateToLoginScreen() {
        sendEffect(RegisterEffect.NavigateToLoginScreen)
    }

    override fun emailChanged(newEmail: String) {
        updateState { this.copy(form = form.copy(email = newEmail)) }
    }

    override fun nameChanged(newName: String) {
        updateState { this.copy(form = form.copy(fullName = newName)) }
    }

    override fun passwordChanged(newPassword: String) {
        updateState { this.copy(form = form.copy(password = newPassword)) }
    }

    override fun confirmPasswordChanged(confirmPassword: String) {
        updateState { this.copy(form = form.copy(confirmPassword = confirmPassword)) }
    }

    override fun phoneChanged(newPhone: String) {
        updateState { this.copy(form = form.copy(phoneNumber = newPhone)) }
    }

    private fun validateRegisterForm(): RegisterFormError? {
        val form = state.value.form

        return when {
            form.fullName.isBlank() -> RegisterFormError.NameFormError.EmptyFullName
            form.fullName.trim().length < 3 -> RegisterFormError.NameFormError.InvalidFullName
            !form.fullName.contains(" ") -> RegisterFormError.NameFormError.InvalidFullName

            form.email.isBlank() -> RegisterFormError.EmailFormError.EmptyEmail
            !emailRegex.matches(form.email.trim()) -> RegisterFormError.EmailFormError.InvalidEmail

            form.phoneNumber.isBlank() -> RegisterFormError.PhoneFormError.EmptyPhone
            !phoneRegex.matches(form.phoneNumber) -> RegisterFormError.PhoneFormError.InvalidPhone

            form.password.isBlank() -> RegisterFormError.PasswordFormError.EmptyPassword
            !isStrongPassword(form.password) -> RegisterFormError.PasswordFormError.WeakPassword

            form.confirmPassword.isBlank() -> RegisterFormError.ConfirmPasswordFormError.EmptyConfirmPassword
            form.password != form.confirmPassword -> RegisterFormError.ConfirmPasswordFormError.PasswordsDoNotMatch

            else -> null
        }
    }

    private fun isStrongPassword(password: String): Boolean {
        if (password.length < 8) return false
        if (!password.any { it.isUpperCase() }) return false
        if (!password.any { it.isLowerCase() }) return false
        if (!password.any { it.isDigit() }) return false

        return true
    }
}
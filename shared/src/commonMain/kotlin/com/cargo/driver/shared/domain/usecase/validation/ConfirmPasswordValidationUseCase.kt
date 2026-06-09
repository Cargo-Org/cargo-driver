package com.cargo.driver.shared.domain.usecase.validation

import com.cargo.driver.shared.domain.model.validation.ConfirmPasswordValidationResult


class ConfirmPasswordValidationUseCase {

    operator fun invoke(password: String, confirmPassword: String): ConfirmPasswordValidationResult =
        when {
            confirmPassword.isBlank() -> ConfirmPasswordValidationResult.EmptyConfirmPassword
            password != confirmPassword -> ConfirmPasswordValidationResult.PasswordsDoNotMatch
            else -> ConfirmPasswordValidationResult.Valid
        }
}
package com.cargo.driver.shared.domain.usecase.validation

import com.cargo.driver.shared.domain.model.validation.ValidationResult


class ConfirmPasswordValidationUseCase {

    operator fun invoke(password: String, confirmPassword: String): ValidationResult =
        when {
            confirmPassword.isBlank() -> ValidationResult.Empty
            password != confirmPassword -> ValidationResult.Invalid
            else -> ValidationResult.Valid
        }
}
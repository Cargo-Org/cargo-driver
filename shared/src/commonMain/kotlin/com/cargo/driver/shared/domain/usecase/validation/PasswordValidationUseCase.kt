package com.cargo.driver.shared.domain.usecase.validation

import com.cargo.driver.shared.domain.model.validation.ValidationResult


class PasswordValidationUseCase {

    operator fun invoke(password: String): ValidationResult {
        val weakPassword = ValidationResult.Invalid
        val emptyPassword = ValidationResult.Empty

        if (password.isBlank()) return emptyPassword
        else if (password.length < 8) return weakPassword
        else if (!password.any { it.isUpperCase() }) return weakPassword
        else if (!password.any { it.isLowerCase() }) return weakPassword
        else if (!password.any { it.isDigit() }) return weakPassword

        return ValidationResult.Valid
    }
}
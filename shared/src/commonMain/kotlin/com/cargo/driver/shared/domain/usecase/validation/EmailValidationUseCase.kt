package com.cargo.driver.shared.domain.usecase.validation

import com.cargo.driver.shared.domain.model.validation.ValidationResult


class EmailValidationUseCase {
    companion object {
        private val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    }

    operator fun invoke(email: String): ValidationResult =
        if (email.trim().isBlank()) ValidationResult.Empty
        else if (!regex.matches(email.trim())) ValidationResult.Invalid
        else ValidationResult.Valid
}

package com.cargo.driver.shared.domain.usecase.validation

import com.cargo.driver.shared.domain.model.validation.EmailValidationResult


class EmailValidationUseCase {
    private val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

    operator fun invoke(email: String): EmailValidationResult =
        if (email.trim().isBlank()) EmailValidationResult.EmptyEmail
        else if (!regex.matches(email.trim())) EmailValidationResult.InvalidEmail
        else EmailValidationResult.Valid
}

package com.cargo.driver.shared.domain.usecase.validation

import com.cargo.driver.shared.domain.model.validation.ValidationResult


class PhoneValidationUseCase {
    companion object {
        private val regex = Regex("^01[0125][0-9]{8}$")
    }

    operator fun invoke(phone: String): ValidationResult =
        when {
            phone.isBlank() -> ValidationResult.Empty
            !regex.matches(phone.trim()) -> ValidationResult.Invalid
            else -> ValidationResult.Valid
        }
}
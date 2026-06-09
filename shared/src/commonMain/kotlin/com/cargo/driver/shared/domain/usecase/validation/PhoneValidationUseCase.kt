package com.cargo.driver.shared.domain.usecase.validation

import com.cargo.driver.shared.domain.model.validation.PhoneValidationResult


class PhoneValidationUseCase {
    companion object {
        private val regex = Regex("^01[0125][0-9]{8}$")
    }

    operator fun invoke(phone: String): PhoneValidationResult =
        when {
            phone.isBlank() -> PhoneValidationResult.EmptyPhone
            !regex.matches(phone.trim()) -> PhoneValidationResult.InvalidPhone
            else -> PhoneValidationResult.Valid
        }
}
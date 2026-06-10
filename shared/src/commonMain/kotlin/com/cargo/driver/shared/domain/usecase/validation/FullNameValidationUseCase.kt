package com.cargo.driver.shared.domain.usecase.validation

import com.cargo.driver.shared.domain.model.validation.ValidationResult


class FullNameValidationUseCase {

    operator fun invoke(fullName: String): ValidationResult =
        if (fullName.isBlank()) ValidationResult.Empty
        else if (fullName.trim().length < 3) ValidationResult.Invalid
        else if (!fullName.trim().contains(" ")) ValidationResult.Invalid
        else ValidationResult.Valid
}
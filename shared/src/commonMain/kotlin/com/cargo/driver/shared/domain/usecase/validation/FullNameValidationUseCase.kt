package com.cargo.driver.shared.domain.usecase.validation

import com.cargo.driver.shared.domain.model.validation.NameValidationResult


class FullNameValidationUseCase {

    operator fun invoke(fullName: String): NameValidationResult =
        if (fullName.isBlank()) NameValidationResult.EmptyFullName
        else if (fullName.trim().length < 3) NameValidationResult.InvalidFullName
        else if (!fullName.trim().contains(" ")) NameValidationResult.InvalidFullName
        else NameValidationResult.Valid
}
package com.cargo.driver.shared.domain.model.register

sealed interface RegisterResponse

data object RegisterSuccess : RegisterResponse


sealed interface RegisterError : RegisterResponse {
    data object NoInternet : RegisterError
    data object EmailValidation : RegisterError
    data object FirstNameValidation : RegisterError
    data object LastNameValidation : RegisterError
    data object EmailAlreadyExists : RegisterError
    data object PasswordValidation : RegisterError
    data object PhoneNumberValidation : RegisterError
    data object Unknown : RegisterError
}
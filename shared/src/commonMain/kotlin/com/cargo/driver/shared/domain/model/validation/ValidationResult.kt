package com.cargo.driver.shared.domain.model.validation

sealed interface ValidationResult

sealed interface NameValidationResult: ValidationResult{
    object EmptyFullName : NameValidationResult
    object InvalidFullName : NameValidationResult
    object Valid : NameValidationResult
}

sealed interface EmailValidationResult: ValidationResult{
    object EmptyEmail : EmailValidationResult
    object InvalidEmail : EmailValidationResult
    object Valid : EmailValidationResult
}

sealed interface PhoneValidationResult: ValidationResult{
    object EmptyPhone : PhoneValidationResult
    object InvalidPhone : PhoneValidationResult
    object Valid : PhoneValidationResult
}

sealed interface PasswordValidationResult: ValidationResult{
    object EmptyPassword : PasswordValidationResult
    object WeakPassword : PasswordValidationResult
    object Valid : PasswordValidationResult
}

sealed interface ConfirmPasswordValidationResult: ValidationResult{
    object EmptyConfirmPassword : ConfirmPasswordValidationResult
    object PasswordsDoNotMatch : ConfirmPasswordValidationResult
    object Valid : ConfirmPasswordValidationResult
}
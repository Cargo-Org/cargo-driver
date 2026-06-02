package com.example.carog_driver.presentation.screen.register.mapper

import carog_driver.composeapp.generated.resources.Res
import carog_driver.composeapp.generated.resources.email_already_exists
import carog_driver.composeapp.generated.resources.empty_confirm_password
import carog_driver.composeapp.generated.resources.empty_email
import carog_driver.composeapp.generated.resources.empty_full_name
import carog_driver.composeapp.generated.resources.empty_password
import carog_driver.composeapp.generated.resources.empty_phone
import carog_driver.composeapp.generated.resources.invalid_email
import carog_driver.composeapp.generated.resources.invalid_full_name
import carog_driver.composeapp.generated.resources.invalid_phone
import carog_driver.composeapp.generated.resources.no_internet
import carog_driver.composeapp.generated.resources.passwords_do_not_match
import carog_driver.composeapp.generated.resources.unknown_error
import carog_driver.composeapp.generated.resources.weak_password
import com.cargo.driver.shared.domain.model.register.RegisterError
import com.example.carog_driver.presentation.screen.register.uimodel.RegisterFormError
import org.jetbrains.compose.resources.StringResource


fun RegisterFormError.toStringResource(): StringResource {
    return when (this) {
        RegisterFormError.NameFormError.EmptyFullName -> Res.string.empty_full_name
        RegisterFormError.NameFormError.InvalidFullName -> Res.string.invalid_full_name

        RegisterFormError.EmailFormError.EmptyEmail -> Res.string.empty_email
        RegisterFormError.EmailFormError.InvalidEmail -> Res.string.invalid_email
        RegisterFormError.EmailFormError.EmailAlreadyExists -> Res.string.email_already_exists

        RegisterFormError.PhoneFormError.EmptyPhone -> Res.string.empty_phone
        RegisterFormError.PhoneFormError.InvalidPhone -> Res.string.invalid_phone

        RegisterFormError.PasswordFormError.EmptyPassword -> Res.string.empty_password
        RegisterFormError.PasswordFormError.WeakPassword -> Res.string.weak_password

        RegisterFormError.ConfirmPasswordFormError.EmptyConfirmPassword -> Res.string.empty_confirm_password
        RegisterFormError.ConfirmPasswordFormError.PasswordsDoNotMatch -> Res.string.passwords_do_not_match

        RegisterFormError.NoInternet -> Res.string.no_internet
        RegisterFormError.Unknown -> Res.string.unknown_error
    }
}


fun RegisterError.toRegisterFormError(): RegisterFormError {
    return when (this) {
        RegisterError.EmailAlreadyExists -> RegisterFormError.EmailFormError.EmailAlreadyExists
        RegisterError.EmailValidation -> RegisterFormError.EmailFormError.InvalidEmail
        RegisterError.NoInternet -> RegisterFormError.NoInternet
        RegisterError.PasswordValidation -> RegisterFormError.PasswordFormError.WeakPassword
        RegisterError.PhoneNumberValidation -> RegisterFormError.PhoneFormError.InvalidPhone
        RegisterError.FirstNameValidation -> RegisterFormError.NameFormError.InvalidFullName
        RegisterError.LastNameValidation -> RegisterFormError.NameFormError.InvalidFullName
        RegisterError.Unknown -> RegisterFormError.Unknown
    }
}
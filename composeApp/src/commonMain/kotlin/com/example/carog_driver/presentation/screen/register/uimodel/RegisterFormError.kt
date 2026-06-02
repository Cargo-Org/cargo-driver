package com.example.carog_driver.presentation.screen.register.uimodel


sealed interface RegisterFormError {

    sealed interface NameFormError: RegisterFormError{
        data object EmptyFullName : NameFormError
        data object InvalidFullName : NameFormError
    }

    sealed interface EmailFormError: RegisterFormError{
        data object EmptyEmail : EmailFormError
        data object InvalidEmail : EmailFormError
        data object EmailAlreadyExists : EmailFormError
    }

    sealed interface PhoneFormError: RegisterFormError{
        data object EmptyPhone : PhoneFormError
        data object InvalidPhone : PhoneFormError
    }

    sealed interface PasswordFormError: RegisterFormError{
        data object EmptyPassword : PasswordFormError
        data object WeakPassword : PasswordFormError
    }

    sealed interface ConfirmPasswordFormError: RegisterFormError{
        data object EmptyConfirmPassword : ConfirmPasswordFormError
        data object PasswordsDoNotMatch : ConfirmPasswordFormError
    }

    data object NoInternet : RegisterFormError

    data object Unknown : RegisterFormError
}

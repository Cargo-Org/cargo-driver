package com.cargo.driver.shared.data.mapper.register

import com.cargo.driver.shared.data.remote.dto.GeneralClientErrorDTO
import com.cargo.driver.shared.data.remote.dto.ValidationClientErrorDTO
import com.cargo.driver.shared.domain.model.register.RegisterError


internal fun ValidationClientErrorDTO.toRegisterError(): RegisterError {
    return when {
        errors.keys.any {
            it.contains("firstname", ignoreCase = true)
        } -> RegisterError.FirstNameValidation
        errors.keys.any {
            it.contains("lastname", ignoreCase = true)
        } -> RegisterError.LastNameValidation
        errors.keys.any {
            it.contains("email", ignoreCase = true)
        } -> RegisterError.EmailValidation
        errors.keys.any {
            it.contains("phone", ignoreCase = true)
        } -> RegisterError.PhoneNumberValidation
        errors.keys.any {
            it.contains("password", ignoreCase = true)
        } -> RegisterError.PasswordValidation
        else -> RegisterError.Unknown
    }
}

internal fun GeneralClientErrorDTO.toRegisterError(): RegisterError {
    return when {
        title.contains("email", ignoreCase = true) ->
            RegisterError.EmailAlreadyExists
        else -> RegisterError.Unknown
    }
}
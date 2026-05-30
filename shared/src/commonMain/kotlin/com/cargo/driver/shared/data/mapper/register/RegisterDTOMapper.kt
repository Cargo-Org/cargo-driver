package com.cargo.driver.shared.data.mapper.register

import com.cargo.driver.shared.data.remote.dto.register.AuthorizationErrorRegisterResponseDTO
import com.cargo.driver.shared.data.remote.dto.register.RegisterRequestDTO
import com.cargo.driver.shared.data.remote.dto.register.RegisterResponseDTO
import com.cargo.driver.shared.data.remote.dto.register.SuccessRegisterResponseDTO
import com.cargo.driver.shared.data.remote.dto.register.ValidationErrorRegisterResponseDTO
import com.cargo.driver.shared.domain.model.register.ErrorRegisterResponse
import com.cargo.driver.shared.domain.model.register.RegisterRequest
import com.cargo.driver.shared.domain.model.register.RegisterResponse
import com.cargo.driver.shared.domain.model.register.SuccessRegisterResponse


fun RegisterResponseDTO.toModel(): RegisterResponse {
    return when (this) {
        is AuthorizationErrorRegisterResponseDTO -> {
            ErrorRegisterResponse(message = this.detail)
        }

        is ValidationErrorRegisterResponseDTO -> {
            val errors = this.errors
            val firstErrorKey = errors.keys.firstOrNull()

            if (firstErrorKey == null){
                //TODO: localized error message
                ErrorRegisterResponse(
                    message = "Network issue, please try again later"
                )
            }else{
                ErrorRegisterResponse(
                    message = errors[firstErrorKey].toString()
                )
            }
        }
        SuccessRegisterResponseDTO -> SuccessRegisterResponse
    }
}


fun RegisterRequestDTO.toModel(): RegisterRequest {
    return RegisterRequest(
        email = this.email,
        password = this.password,
        phoneNumber = this.phoneNumber,
        fullName = "${this.fistName} ${this.lastName}"
    )
}
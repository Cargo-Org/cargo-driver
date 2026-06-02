package com.cargo.driver.shared.data.mapper.register

import com.cargo.driver.shared.data.remote.dto.register.RegisterRequestDTO
import com.cargo.driver.shared.domain.model.register.RegisterRequest


fun RegisterRequest.toDTO(): RegisterRequestDTO {
    return RegisterRequestDTO(
        email = email,
        password = password,
        phoneNumber = phoneNumber,
        firstName = fistName,
        lastName = lastName
    )
}
package com.cargo.driver.shared.domain.mapper.register

import com.cargo.driver.shared.data.remote.dto.register.RegisterRequestDTO
import com.cargo.driver.shared.domain.model.register.RegisterRequest


fun RegisterRequest.toDTO(): RegisterRequestDTO {
    return RegisterRequestDTO(
        email = this.email,
        password = this.password,
        phoneNumber = this.phoneNumber,
        fistName = this.fistName,
        lastName = this.lastName,
    )
}
package com.cargo.driver.shared.data.remote.dto.register

data class RegisterRequestDTO(
    val email: String,
    val password: String,
    val phoneNumber: String,
    val fistName: String,
    val lastName: String
)
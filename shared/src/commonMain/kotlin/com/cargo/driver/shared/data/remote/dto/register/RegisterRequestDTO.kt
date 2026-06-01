package com.cargo.driver.shared.data.remote.dto.register

import kotlinx.serialization.Serializable


@Serializable
data class RegisterRequestDTO(
    val email: String,
    val password: String,
    val phoneNumber: String,
    val fistName: String,
    val lastName: String
)
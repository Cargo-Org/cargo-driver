package com.cargo.driver.shared.data.remote.dto.register

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class RegisterRequestDTO(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("phoneNumber")
    val phoneNumber: String,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("lastName")
    val lastName: String
)
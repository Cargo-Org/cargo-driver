package com.cargo.driver.shared.data.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val driverId: String,
    val fullName: String,
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiresIn: Int,
    val refreshTokenExpiresIn: Int
)
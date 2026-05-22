package com.cargo.driver.shared.data.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class ProfileRequestDto(
    val accessToken: String,
)
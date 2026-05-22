package com.cargo.driver.shared.data.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponseDto(
    val driverId: String,
    val email: String,
    val fullName: String,
    val phoneNumber: String,
    val isEmailVerified: Boolean,
    val onboardingStatus: String,
    val hasRejectedDocuments: Boolean
)
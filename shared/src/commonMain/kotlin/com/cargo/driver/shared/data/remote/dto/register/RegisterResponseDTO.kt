package com.cargo.driver.shared.data.remote.dto.register

import kotlinx.serialization.Serializable

@Serializable
sealed interface RegisterResponseDTO

@Serializable
object SuccessRegisterResponseDTO: RegisterResponseDTO
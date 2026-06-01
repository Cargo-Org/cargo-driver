package com.cargo.driver.shared.data.remote.dto

import kotlinx.serialization.Serializable

sealed interface ClientErrorDTO

@Serializable
data class ValidationClientErrorDTO(
    val status: Int,
    val errors: Map<String, List<String>>
): ClientErrorDTO


@Serializable
data class GeneralClientErrorDTO(
    val status: Int,
    val title: String,
    val details: String
): ClientErrorDTO

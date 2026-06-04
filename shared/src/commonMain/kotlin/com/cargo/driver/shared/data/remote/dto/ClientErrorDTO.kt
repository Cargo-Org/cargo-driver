package com.cargo.driver.shared.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface ClientErrorDTO

@Serializable
data class ValidationClientErrorDTO(
    @SerialName("status")
    val status: Int,
    @SerialName("errors")
    val errors: Map<String, List<String>>
): ClientErrorDTO


@Serializable
data class GeneralClientErrorDTO(
    @SerialName("status")
    val status: Int,
    @SerialName("title")
    val title: String,
    @SerialName("detail")
    val details: String
): ClientErrorDTO
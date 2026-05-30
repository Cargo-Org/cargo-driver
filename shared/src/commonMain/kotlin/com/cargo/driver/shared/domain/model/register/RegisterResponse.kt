package com.cargo.driver.shared.domain.model.register

sealed interface RegisterResponse

object SuccessRegisterResponse: RegisterResponse

data class ErrorRegisterResponse(
    val message: String
): RegisterResponse
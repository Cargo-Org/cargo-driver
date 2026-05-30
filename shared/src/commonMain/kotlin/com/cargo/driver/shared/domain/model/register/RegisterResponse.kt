package com.cargo.driver.shared.domain.model.register

sealed interface RegisterResponse

object SuccessRegisterResponse: RegisterResponse

data class AuthorizationErrorRegisterResponse(
    val message: String
): RegisterResponse

data class ValidationErrorRegisterResponse(
    val message: String
): RegisterResponse
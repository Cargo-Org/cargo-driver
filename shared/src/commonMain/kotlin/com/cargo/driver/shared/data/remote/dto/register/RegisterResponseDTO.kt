package com.cargo.driver.shared.data.remote.dto.register

sealed interface RegisterResponseDTO


object SuccessRegisterResponseDTO: RegisterResponseDTO

data class AuthorizationErrorRegisterResponseDTO(
    val status: Int,
    val title: String,
    val detail: String
): RegisterResponseDTO


data class ValidationErrorRegisterResponseDTO(
    val status: Int,
    val errors: Map<ValidationError, List<String>>
): RegisterResponseDTO

enum class ValidationError(val errorKey: String){
    EMAIL("Email"),
    PHONE_NUMBER("PhoneNumber"),
    PASSWORD("Password"),
}
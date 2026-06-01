package com.cargo.driver.shared.domain.model.register

data class RegisterRequest(
    val email: String,
    val password: String,
    val phoneNumber: String,
    val fullName: String
) {

    val fistName: String
        get() = fullName
            .trim()
            .substringBefore(" ")

    val lastName: String
        get() = fullName
            .trim()
            .substringAfter(" ", "")
}
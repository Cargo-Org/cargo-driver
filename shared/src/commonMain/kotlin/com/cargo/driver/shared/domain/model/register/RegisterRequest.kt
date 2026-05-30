package com.cargo.driver.shared.domain.model.register

data class RegisterRequest(
    val email: String,
    val password: String,
    val phoneNumber: String,
    val fullName: String
) {
    val fistName: String get() = fullName.substring(0, fullName.indexOf(" "))

    val lastName: String
        get() = fullName.substring(fullName.indexOf(" ") + 1)

}
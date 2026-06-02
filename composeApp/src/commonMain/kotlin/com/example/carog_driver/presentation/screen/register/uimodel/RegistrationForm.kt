package com.example.carog_driver.presentation.screen.register.uimodel

data class RegistrationForm(
    val fullName: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val password: String = "",
    val confirmPassword: String = ""
) {
    val normalizedPhoneNumber: String
        get() = "+2$phoneNumber"
}
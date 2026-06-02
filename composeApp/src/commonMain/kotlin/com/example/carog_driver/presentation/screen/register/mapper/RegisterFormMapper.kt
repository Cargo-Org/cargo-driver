package com.example.carog_driver.presentation.screen.register.mapper

import com.cargo.driver.shared.domain.model.register.RegisterRequest
import com.example.carog_driver.presentation.screen.register.uimodel.RegistrationForm


fun RegistrationForm.toModel(): RegisterRequest{
    return RegisterRequest(
        email = email,
        password = password,
        phoneNumber = normalizedPhoneNumber,
        fullName = fullName,
    )
}
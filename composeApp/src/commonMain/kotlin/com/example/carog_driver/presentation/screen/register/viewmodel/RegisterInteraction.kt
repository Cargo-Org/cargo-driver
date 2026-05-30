package com.example.carog_driver.presentation.screen.register.viewmodel

import com.cargo.driver.shared.domain.model.register.RegisterRequest

interface RegisterInteraction {
    fun register(registerRequest: RegisterRequest)
    fun registerWithGoogle()
    fun navigateToLoginScreen()
}
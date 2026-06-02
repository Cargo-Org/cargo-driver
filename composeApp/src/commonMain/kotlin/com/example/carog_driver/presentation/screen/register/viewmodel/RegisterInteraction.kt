package com.example.carog_driver.presentation.screen.register.viewmodel


interface RegisterFormInteraction {
    fun emailChanged(newEmail: String)
    fun nameChanged(newName: String)
    fun passwordChanged(newPassword: String)
    fun confirmPasswordChanged(confirmPassword: String)
    fun phoneChanged(newPhone: String)
}

interface RegisterInteraction: RegisterFormInteraction{
    fun register()
    fun registerWithGoogle()
    fun navigateToLoginScreen()
}
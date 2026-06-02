package com.example.carog_driver.presentation.screen.register.viewmodel


sealed interface RegisterEffect {
    object NavigateToOtpScreen : RegisterEffect
    object NavigateToLoginScreen : RegisterEffect

    //TODO: showToast method
}
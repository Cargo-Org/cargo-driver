package com.example.carog_driver.presentation.screen.splash.viewmodel

sealed interface SplashEffect  {
    object NavigateToOnboarding : SplashEffect
    object NavigateToLogin : SplashEffect
    object NavigateToHome : SplashEffect
}
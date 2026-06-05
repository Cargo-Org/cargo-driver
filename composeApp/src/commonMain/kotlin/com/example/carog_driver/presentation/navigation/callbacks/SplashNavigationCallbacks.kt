package com.example.carog_driver.presentation.navigation.callbacks

data class SplashNavigationCallbacks(
    val onNavigateToOnboarding: () -> Unit,
    val onNavigateToLogin: () -> Unit,
    val onNavigateToHome: () -> Unit
)
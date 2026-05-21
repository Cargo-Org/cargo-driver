package com.example.carog_driver.presentation.screen.onboarding.viewmodel

sealed class OnboardingEffect {

    data object NavigateNext : OnboardingEffect()
}
package com.example.carog_driver.presentation.onboarding.viewmodel

sealed class OnboardingEffect {

    data object NavigateNext : OnboardingEffect()
}
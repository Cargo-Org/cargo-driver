package com.example.carog_driver.presentation.onboarding.viewmodel

sealed interface OnboardingEffect {

    data object NavigateNext : OnboardingEffect
}
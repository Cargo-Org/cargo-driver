package com.example.carog_driver.presentation.onboarding

import org.jetbrains.compose.resources.DrawableResource


data class OnboardingItem(
    val image: DrawableResource,
    val title: String,
    val subtitle: String
)
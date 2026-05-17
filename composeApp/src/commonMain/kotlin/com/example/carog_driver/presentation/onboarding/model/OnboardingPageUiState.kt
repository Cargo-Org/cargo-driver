package com.example.carog_driver.presentation.onboarding.model

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class OnboardingPageUiState(
    val image: DrawableResource,
    val title: StringResource,
    val subtitle: StringResource
)
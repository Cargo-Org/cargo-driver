package com.example.carog_driver.presentation.screen.onboarding.viewmodel

import carog_driver.composeapp.generated.resources.*
import org.jetbrains.compose.resources.*
data class OnboardingPageModel(
    val image: DrawableResource,
    val title: StringResource,
    val subtitle: StringResource
)
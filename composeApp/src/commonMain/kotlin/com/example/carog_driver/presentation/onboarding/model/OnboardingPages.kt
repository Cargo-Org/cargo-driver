package com.example.carog_driver.presentation.onboarding.model

import carog_driver.composeapp.generated.resources.*

fun getOnboardingPages(): List<OnboardingPageUiState>{

    return listOf(
        OnboardingPageUiState(
            image = Res.drawable.onboarding_1,
            title = Res.string.onboarding_title_1,
            subtitle = Res.string.onboarding_subtitle_1
        ),
        OnboardingPageUiState(
            image = Res.drawable.onboarding_2,
            title = Res.string.onboarding_title_2,
            subtitle = Res.string.onboarding_subtitle_2
        ),
        OnboardingPageUiState(
            image = Res.drawable.onboarding_3,
            title = Res.string.onboarding_title_3,
            subtitle = Res.string.onboarding_subtitle_3
        )
    )
}
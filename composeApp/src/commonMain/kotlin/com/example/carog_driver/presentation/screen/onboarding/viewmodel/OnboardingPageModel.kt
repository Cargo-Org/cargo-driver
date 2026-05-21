package com.example.carog_driver.presentation.screen.onboarding.viewmodel

import carog_driver.composeapp.generated.resources.*
import org.jetbrains.compose.resources.*
data class OnboardingPageModel(
    val image: DrawableResource,
    val title: StringResource,
    val subtitle: StringResource
)

fun getOnboardingPages(): List<OnboardingPageModel>{

    return listOf(
        OnboardingPageModel(
            image = Res.drawable.onboarding_1,
            title = Res.string.onboarding_title_1,
            subtitle = Res.string.onboarding_subtitle_1
        ),
        OnboardingPageModel(
            image = Res.drawable.onboarding_2,
            title = Res.string.onboarding_title_2,
            subtitle = Res.string.onboarding_subtitle_2
        ),
        OnboardingPageModel(
            image = Res.drawable.onboarding_3,
            title = Res.string.onboarding_title_3,
            subtitle = Res.string.onboarding_subtitle_3
        )
    )
}


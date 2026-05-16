package com.example.carog_driver.presentation.onboarding

import carog_driver.composeapp.generated.resources.*

fun getOnboardingPages(): List<OnboardingItem>{

    return listOf(
        OnboardingItem(
            image = Res.drawable.onboarding_1,
            title = "Get Delivery Requests",
            subtitle = "Receive nearby trips, check package details, and start your delivery journey with ease."
        ),
        OnboardingItem(
            image = Res.drawable.onboarding_2,
            title = "Pickup Made Simple",
            subtitle = "Confirm pickups, scan packages, and follow the best route to your destination."
        ),
        OnboardingItem(
            image = Res.drawable.onboarding_3,
            title = "Earn After Every Trip",
            subtitle = "Complete deliveries successfully and track your earnings directly from the app."
        )
    )
}
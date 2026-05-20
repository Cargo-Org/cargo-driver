package com.example.carog_driver.di

import com.example.carog_driver.presentation.onboarding.viewmodel.OnboardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        OnboardingViewModel(
            completeOnboarding = get()
        )
    }
}
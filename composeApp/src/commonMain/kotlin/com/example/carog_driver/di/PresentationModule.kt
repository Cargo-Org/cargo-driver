package com.example.carog_driver.di

import com.example.carog_driver.presentation.screen.onboarding.viewmodel.OnboardingViewModel
import com.example.carog_driver.presentation.screen.splash.viewmodel.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        OnboardingViewModel(
            completeOnboarding = get()
        )
    }

    viewModel { SplashViewModel(get()) }
}
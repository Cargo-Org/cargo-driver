package com.example.carog_driver.presentation.screen.onboarding.viewmodel

import com.cargo.driver.shared.domain.usecase.onboarding.CompleteOnboardingUseCase
import com.example.carog_driver.presentation.base.BaseViewModel

class OnboardingViewModel(
    private val completeOnboarding: CompleteOnboardingUseCase
) : BaseViewModel<Unit, OnboardingEffect>(), OnboardingInteraction {

    override fun onSkipClick() {
        completeOnboardingAndNavigate()
    }

    override fun onFinishClick() {
        completeOnboardingAndNavigate()
    }

    private fun completeOnboardingAndNavigate() {
        tryToExecute(
            block = {
                completeOnboarding()
            },
            onSuccess = {
                sendEffect(OnboardingEffect.NavigateNext)
            }
        )
    }
}
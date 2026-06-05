package com.example.carog_driver.presentation.screen.splash.viewmodel

import com.cargo.driver.shared.domain.usecase.onboarding.GetOnboardingCompletedUseCase
import com.example.carog_driver.presentation.base.BaseViewModel


class SplashViewModel (
    private val isOnboardingFirstTimeUseCase: GetOnboardingCompletedUseCase,
): BaseViewModel<SplashState, SplashEffect>(SplashState()) {

    fun determineNextDestination() {
        updateState { copy(isLoading = true) }

        tryToExecute(
            onSuccess = {
                updateState { copy(isLoading = false) }
                if (it) {
                    sendEffect(SplashEffect.NavigateToOnboarding)
                } else {
                    sendEffect(SplashEffect.NavigateToLogin)
                }
            },
            onError = {
                updateState { copy(isLoading = false) }
            },
            block = { isOnboardingFirstTimeUseCase() }
        )
    }
}
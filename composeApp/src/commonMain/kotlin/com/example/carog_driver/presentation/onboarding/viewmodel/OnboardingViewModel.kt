package com.example.carog_driver.presentation.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cargo.driver.shared.domain.usecase.onboarding.CompleteOnboardingUseCase
import com.cargo.driver.shared.domain.usecase.onboarding.ObserveOnboardingCompletedUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val observeOnboardingCompleted: ObserveOnboardingCompletedUseCase,
    private val completeOnboarding: CompleteOnboardingUseCase
) : ViewModel(), OnboardingInteraction {

    private val _effect = Channel<OnboardingEffect>(
        capacity = Channel.Factory.BUFFERED
    )
    val effect = _effect.receiveAsFlow()

    override fun onSkipClick() {
        completeOnboardingAndNavigate()
    }

    override fun onFinishClick() {
        completeOnboardingAndNavigate()
    }

    private fun completeOnboardingAndNavigate() {
        viewModelScope.launch {
            completeOnboarding()
            _effect.send(OnboardingEffect.NavigateNext)
        }
    }
}
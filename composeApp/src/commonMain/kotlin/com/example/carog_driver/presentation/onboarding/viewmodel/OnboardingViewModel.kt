package com.example.carog_driver.presentation.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cargo.driver.shared.domain.usecase.onboarding.CompleteOnboardingUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val completeOnboarding: CompleteOnboardingUseCase
) : ViewModel(), OnboardingInteraction {

    private val _effect = MutableSharedFlow<OnboardingEffect>(
        replay = 0,
        extraBufferCapacity = 1
    )

    val effect: SharedFlow<OnboardingEffect> = _effect.asSharedFlow()

    override fun onSkipClick() {
        completeOnboardingAndNavigate()
    }

    override fun onFinishClick() {
        completeOnboardingAndNavigate()
    }

    private fun completeOnboardingAndNavigate() {
        viewModelScope.launch {
            completeOnboarding()
            _effect.emit(OnboardingEffect.NavigateNext)
        }
    }
}
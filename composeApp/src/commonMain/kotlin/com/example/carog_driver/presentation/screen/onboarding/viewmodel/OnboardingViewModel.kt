package com.example.carog_driver.presentation.screen.onboarding.viewmodel

import carog_driver.composeapp.generated.resources.Res
import carog_driver.composeapp.generated.resources.onboarding_1
import carog_driver.composeapp.generated.resources.onboarding_2
import carog_driver.composeapp.generated.resources.onboarding_3
import carog_driver.composeapp.generated.resources.onboarding_subtitle_1
import carog_driver.composeapp.generated.resources.onboarding_subtitle_2
import carog_driver.composeapp.generated.resources.onboarding_subtitle_3
import carog_driver.composeapp.generated.resources.onboarding_title_1
import carog_driver.composeapp.generated.resources.onboarding_title_2
import carog_driver.composeapp.generated.resources.onboarding_title_3
import com.cargo.driver.shared.domain.usecase.onboarding.CompleteOnboardingUseCase
import com.example.carog_driver.presentation.base.BaseViewModel

class OnboardingViewModel(
    private val completeOnboarding: CompleteOnboardingUseCase
) : BaseViewModel<OnboardingState, OnboardingEffect>(OnboardingState()), OnboardingInteraction {

    init {
        updateState {
            copy(pages = getOnboardingPages())
        }
    }

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

    private fun getOnboardingPages(): List<OnboardingPageModel>{

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
}
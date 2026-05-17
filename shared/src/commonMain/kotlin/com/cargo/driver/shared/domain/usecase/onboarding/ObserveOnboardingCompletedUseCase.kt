package com.cargo.driver.shared.domain.usecase.onboarding

import com.cargo.driver.shared.domain.repository.UserPreferencesRepository

class ObserveOnboardingCompletedUseCase(
    private val repository: UserPreferencesRepository
) {
    operator fun invoke() = repository.observeOnboardingCompleted()
}
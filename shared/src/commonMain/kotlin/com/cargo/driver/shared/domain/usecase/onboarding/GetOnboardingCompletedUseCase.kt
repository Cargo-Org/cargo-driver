package com.cargo.driver.shared.domain.usecase.onboarding

import com.cargo.driver.shared.domain.repository.UserPreferencesRepository

class GetOnboardingCompletedUseCase(
    private val repository: UserPreferencesRepository
) {
    suspend operator fun invoke() = repository.getOnboardingCompleted()
}
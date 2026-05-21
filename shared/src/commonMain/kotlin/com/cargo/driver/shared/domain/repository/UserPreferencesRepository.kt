package com.cargo.driver.shared.domain.repository

interface UserPreferencesRepository {

    suspend fun getOnboardingCompleted(): Boolean

    suspend fun completeOnboarding()

}
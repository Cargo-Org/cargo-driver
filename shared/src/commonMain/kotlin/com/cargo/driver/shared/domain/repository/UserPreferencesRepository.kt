package com.cargo.driver.shared.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {

    fun observeOnboardingCompleted(): Flow<Boolean>

    suspend fun completeOnboarding()

}
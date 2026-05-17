package com.cargo.driver.shared.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {

    suspend fun getOnboardingCompleted(): Boolean

    suspend fun completeOnboarding()

}
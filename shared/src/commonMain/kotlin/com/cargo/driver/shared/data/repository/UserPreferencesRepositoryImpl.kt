package com.cargo.driver.shared.data.repository

import com.cargo.driver.shared.data.local.datasource.preferences.UserPreferencesLocalDataSource
import com.cargo.driver.shared.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow

class UserPreferencesRepositoryImpl(
    private val userPreferencesLocalDataSource: UserPreferencesLocalDataSource
): UserPreferencesRepository {
    override fun observeOnboardingCompleted(): Flow<Boolean> {
        return userPreferencesLocalDataSource.observeOnboardingCompleted()
    }

    override suspend fun completeOnboarding() {
        userPreferencesLocalDataSource.setOnboardingCompleted(
            isCompleted = true
        )
    }
}
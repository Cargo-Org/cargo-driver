package com.cargo.driver.shared.data.repository

import com.cargo.driver.shared.data.local.datasource.preferences.UserPreferencesLocalDataSource
import com.cargo.driver.shared.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow

class UserPreferencesRepositoryImpl(
    private val dataStore: UserPreferencesLocalDataSource
): UserPreferencesRepository {

    override suspend fun getOnboardingCompleted(): Boolean = dataStore.getOnboardingCompleted()


    override suspend fun completeOnboarding() = dataStore.setOnboardingCompleted()

}
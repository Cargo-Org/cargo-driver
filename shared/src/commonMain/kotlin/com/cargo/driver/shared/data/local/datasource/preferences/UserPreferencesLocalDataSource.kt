package com.cargo.driver.shared.data.local.datasource.preferences

import kotlinx.coroutines.flow.Flow

interface UserPreferencesLocalDataSource {

    fun observeOnboardingCompleted(): Flow<Boolean>

    suspend fun setOnboardingCompleted(
        isCompleted: Boolean
    )
}
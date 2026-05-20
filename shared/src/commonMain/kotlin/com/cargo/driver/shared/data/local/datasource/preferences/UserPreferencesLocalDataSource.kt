package com.cargo.driver.shared.data.local.datasource.preferences

import kotlinx.coroutines.flow.Flow

interface UserPreferencesLocalDataSource {

    suspend fun getOnboardingCompleted(): Boolean

    suspend fun setOnboardingCompleted()
}
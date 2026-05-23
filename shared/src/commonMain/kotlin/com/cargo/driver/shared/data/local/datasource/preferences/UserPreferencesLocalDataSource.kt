package com.cargo.driver.shared.data.local.datasource.preferences

interface UserPreferencesLocalDataSource {

    suspend fun getOnboardingCompleted(): Boolean

    suspend fun setOnboardingCompleted()
}
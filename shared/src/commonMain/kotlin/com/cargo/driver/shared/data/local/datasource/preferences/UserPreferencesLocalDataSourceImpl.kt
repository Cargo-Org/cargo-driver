package com.cargo.driver.shared.data.local.datasource.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.cargo.driver.shared.data.local.datastore.PreferencesKeys
import kotlinx.coroutines.flow.first

class UserPreferencesLocalDataSourceImpl(
    private val dataStore: DataStore<Preferences>
) : UserPreferencesLocalDataSource {

    override suspend fun getOnboardingCompleted(): Boolean =
        dataStore.data.first()[PreferencesKeys.IS_ONBOARDING_COMPLETED] ?: false

    override suspend fun setOnboardingCompleted() {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_ONBOARDING_COMPLETED] = true
        }
    }
}
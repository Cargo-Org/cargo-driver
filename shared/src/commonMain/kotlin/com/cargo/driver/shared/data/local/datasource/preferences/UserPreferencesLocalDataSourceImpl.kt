package com.cargo.driver.shared.data.local.datasource.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.cargo.driver.shared.data.local.datastore.PreferencesKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesLocalDataSourceImpl(
    private val dataStore: DataStore<Preferences>
) : UserPreferencesLocalDataSource {

    override fun observeOnboardingCompleted(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[PreferencesKeys.IS_ONBOARDING_COMPLETED] ?: false
        }
    }

    override suspend fun setOnboardingCompleted(
        isCompleted: Boolean
    ) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_ONBOARDING_COMPLETED] = isCompleted
        }
    }
}
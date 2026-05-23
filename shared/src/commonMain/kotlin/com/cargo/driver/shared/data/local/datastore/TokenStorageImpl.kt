package com.cargo.driver.shared.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

class TokenStorageImpl(
    private val dataStore: DataStore<Preferences>
) : TokenStorage {

    override suspend fun saveAccessToken(token: String) {
        dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN] = token
        }
    }

    override suspend fun saveRefreshToken(token: String) {
        dataStore.edit { prefs ->
            prefs[REFRESH_TOKEN] = token
        }
    }

    override suspend fun getAccessToken(): String? {
        return dataStore.data.first()[ACCESS_TOKEN]
    }

    override suspend fun getRefreshToken(): String? {
        return dataStore.data.first()[REFRESH_TOKEN]
    }

    override suspend fun clearTokens() {
        dataStore.edit { prefs ->
            prefs.remove(ACCESS_TOKEN)
            prefs.remove(REFRESH_TOKEN)
        }
    }

    private companion object{
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }
}
package com.cargo.driver.shared.data.local.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferencesKeys {

    val IS_ONBOARDING_COMPLETED = booleanPreferencesKey(
        name = "is_onboarding_completed"
    )
}
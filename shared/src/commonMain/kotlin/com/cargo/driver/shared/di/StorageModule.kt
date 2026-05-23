package com.cargo.driver.shared.di

import com.cargo.driver.shared.data.local.datasource.preferences.UserPreferencesLocalDataSource
import com.cargo.driver.shared.data.local.datasource.preferences.UserPreferencesLocalDataSourceImpl
import com.cargo.driver.shared.data.local.datastore.TokenStorage
import com.cargo.driver.shared.data.local.datastore.TokenStorageImpl
import org.koin.dsl.module

// Shared storage: Room is NOT here (Android only)
// but Shared DAOs (interfaces) can be placed here
val storageModule = module {

    single<UserPreferencesLocalDataSource> {
        UserPreferencesLocalDataSourceImpl(
            dataStore = get()
        )
    }
    // provide shared storage abstractions if any
    single<TokenStorage> {
        TokenStorageImpl(get())
    }
}
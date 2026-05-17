package com.cargo.driver.shared.di
import com.cargo.driver.shared.data.local.datasource.preferences.UserPreferencesLocalDataSource
import com.cargo.driver.shared.data.local.datasource.preferences.UserPreferencesLocalDataSourceImpl
import org.koin.dsl.module


// Shared storage: Room is NOT here (Android only)
// but Shared DAOs (interfaces) can be placed here
val storageModule = module {

    single< UserPreferencesLocalDataSource> {
        UserPreferencesLocalDataSourceImpl(
            dataStore = get()
        )
    }
    // provide shared storage abstractions if any
}
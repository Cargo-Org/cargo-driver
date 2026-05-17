package com.cargo.driver.shared.di
import com.cargo.driver.shared.data.local.datasource.preferences.UserPreferencesLocalDataSource
import com.cargo.driver.shared.data.local.datasource.preferences.UserPreferencesLocalDataSourceImpl
import org.koin.dsl.module


val storageModule = module {

    single< UserPreferencesLocalDataSource> {
        UserPreferencesLocalDataSourceImpl(
            dataStore = get()
        )
    }
    // Room creation
}
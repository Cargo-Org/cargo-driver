package com.cargo.driver.shared.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.cargo.driver.shared.data.local.datastore.TokenStorage
import com.cargo.driver.shared.data.local.datastore.TokenStorageImpl
import com.cargo.driver.shared.data.local.datastore.createDataStore
import org.koin.dsl.module

// Shared storage: Room is NOT here (Android only)
// but Shared DAOs (interfaces) can be placed here
val storageModule = module {
    // provide shared storage abstractions if any
    single<TokenStorage> {
        TokenStorageImpl(get())
    }

    single<DataStore<Preferences>> { createDataStore() }

}
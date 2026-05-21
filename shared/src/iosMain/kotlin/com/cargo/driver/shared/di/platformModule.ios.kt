package com.cargo.driver.shared.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.cargo.driver.shared.data.local.datastore.createDataStore
import com.cargo.driver.shared.data.util.getHttpEngine
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual fun platformModule() = module {

    single<HttpClientEngine> { getHttpEngine() }

    single<DataStore<Preferences>> {
        createDataStore()
    }
}
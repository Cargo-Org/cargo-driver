package com.cargo.driver.shared.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.cargo.driver.shared.data.local.datastore.provideDataStore
import com.cargo.driver.shared.data.remote.client.provideHttpEngine
import io.ktor.client.engine.HttpClientEngine
import org.koin.dsl.module

actual val platformModule = module {

    single<HttpClientEngine> { provideHttpEngine() }

    single<DataStore<Preferences>> {
        provideDataStore()
    }
}

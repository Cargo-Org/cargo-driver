package com.cargo.driver.shared.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.cargo.driver.shared.data.local.datastore.createDataStore
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {

    single<DataStore<Preferences>> {
        createDataStore(
            context = get<Context>()
        )
    }
}
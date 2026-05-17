package com.cargo.driver.shared.di

import android.content.Context
import com.cargo.driver.shared.data.local.datastore.createDataStore

fun createSharedDependencies(
    context: Context
): SharedDependencies {
    return SharedDependencies(
        dataStore = createDataStore(
            context = context
        )
    )
}
package com.cargo.driver.shared.di

import com.cargo.driver.shared.data.local.datastore.createDataStore

fun createSharedDependencies(): SharedDependencies {
    return SharedDependencies(
        dataStore = createDataStore()
    )
}
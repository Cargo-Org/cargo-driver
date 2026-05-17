package com.cargo.driver.shared.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Storage
import androidx.datastore.preferences.core.Preferences

internal const val DATA_STORE_FILE_NAME = "carog_driver.preferences_pb"

fun createDataStore(
    storage: Storage<Preferences>
): DataStore<Preferences> {
    return DataStoreFactory.create(
        storage = storage
    )
}
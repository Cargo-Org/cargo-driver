package com.cargo.driver.shared.di

import org.koin.dsl.module


val androidModule = listOf(
    networkModule,
    storageModule,
    sharedModule,
    platformModule,

    // Provide only Android modules (Room for example)
    module {
    }
)
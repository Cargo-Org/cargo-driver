package com.cargo.driver.shared.di

import org.koin.dsl.module


val iosModules = listOf(
    networkModule,
    storageModule,
    sharedModule,
    platformModule,

    // Provide only IOS modules (Keychain storage for example)
    module {
    }
)

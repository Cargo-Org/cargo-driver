package com.cargo.driver.shared.di

import org.koin.dsl.module


val iosModules = listOf(
    networkModule,
    storageModule,
    sharedModule,

    // Provide only IOS modules (Keychain storage for example)
    module {
    }
)

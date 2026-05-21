package com.cargo.driver.shared.di

import com.cargo.driver.shared.di.platformModule

val iosModules = listOf(
    networkModule,
    storageModule,
    sharedModule,
    platformModule,
    platformModule()
)

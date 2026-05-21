package com.cargo.driver.shared.di

val androidModule = listOf(
    networkModule,
    storageModule,
    sharedModule,
    platformModule()
)
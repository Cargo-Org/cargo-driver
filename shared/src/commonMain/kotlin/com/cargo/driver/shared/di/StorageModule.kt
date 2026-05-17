package com.cargo.driver.shared.di
import org.koin.dsl.module


// Shared storage: Room is NOT here (Android only)
// but Shared DAOs (interfaces) can be placed here
val storageModule = module {
    // provide shared storage abstractions if any
}
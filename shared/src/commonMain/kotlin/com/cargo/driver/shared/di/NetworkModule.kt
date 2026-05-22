package com.cargo.driver.shared.di

import com.cargo.driver.shared.data.remote.client.CargoInterceptor
import com.cargo.driver.shared.data.remote.client.NetworkClient
import org.koin.dsl.module


// Shared networking: Ktor HttpClient + API services
val networkModule = module {
    single {
        CargoInterceptor(get())
    }

    single {
        NetworkClient(
            engine = get(),
            cargoInterceptor = get()
        )
    }
}
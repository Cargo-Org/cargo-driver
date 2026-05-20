package com.cargo.driver.shared.di

import com.cargo.driver.shared.data.util.getHttpEngine
import io.ktor.client.engine.HttpClientEngine
import org.koin.dsl.module

internal actual fun platformModule() = module {
    single<HttpClientEngine> { getHttpEngine() }
}
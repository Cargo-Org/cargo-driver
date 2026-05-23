package com.cargo.driver.shared.data.remote.client

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual fun provideHttpEngine(): HttpClientEngine = OkHttp.create()
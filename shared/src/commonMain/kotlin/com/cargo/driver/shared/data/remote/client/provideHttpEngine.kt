package com.cargo.driver.shared.data.remote.client

import io.ktor.client.engine.HttpClientEngine

expect fun provideHttpEngine(): HttpClientEngine
package com.cargo.driver.shared.data.remote.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkClient(
    private val engine: HttpClientEngine,
) {

    fun create(): HttpClient {
        val client = HttpClient(engine) {

            defaultRequest {
                url(BASE_URL_KEY)
                contentType(ContentType.Application.Json)
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        encodeDefaults = false
                        prettyPrint = false
                    }
                )
            }

            install(HttpTimeout) {
                requestTimeoutMillis = REQUEST_TIMEOUT_KEY
                connectTimeoutMillis = REQUEST_TIMEOUT_KEY
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }

        return client
    }

    private companion object {
        const val BASE_URL_KEY = "https://cargo.northeurope.cloudapp.azure.com/driver/"
        const val REQUEST_TIMEOUT_KEY = 60_000L
    }
}
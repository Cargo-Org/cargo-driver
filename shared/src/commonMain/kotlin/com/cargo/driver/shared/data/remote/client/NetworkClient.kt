package com.cargo.driver.shared.data.remote.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkClient(
    private val engine: HttpClientEngine,
    private val cargoInterceptor: CargoInterceptor
) {
    @PublishedApi
    internal val client: HttpClient by lazy { buildClient() }

    // public http methods

    suspend inline fun <reified T> get(
        path: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T = client.get(path, block).body()

    suspend inline fun <reified T> post(
        path: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T = client.post(path, block).body()

    suspend inline fun <reified T> put(
        path: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T = client.put(path, block).body()

    suspend inline fun <reified T> patch(
        path: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T = client.patch(path, block).body()

    suspend inline fun <reified T> delete(
        path: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T = client.delete(path, block).body()

    // client builder

    private fun buildClient(): HttpClient {
        val client = HttpClient(engine) {
            defaultRequest {
                url(BASE_URL)
                contentType(ContentType.Application.Json)
            }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    encodeDefaults = false
                    prettyPrint = false
                })
            }

            install(HttpTimeout) {
                requestTimeoutMillis = REQUEST_TIMEOUT
                connectTimeoutMillis = REQUEST_TIMEOUT
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }

        cargoInterceptor.install(client)
        return client
    }

    private companion object {
        const val BASE_URL = "https://cargo.northeurope.cloudapp.azure.com/api/driver/"
        const val REQUEST_TIMEOUT = 60_000L
    }
}
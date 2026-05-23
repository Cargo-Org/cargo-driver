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
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkClient(
    private val engine: HttpClientEngine,
    private val cargoInterceptor: CargoInterceptor
) {

    private val client: HttpClient by lazy { buildClient() }

    // public http methods
    suspend fun get(
        path: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): HttpResponse = client.get(path, block)

    suspend fun post(
        path: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): HttpResponse = client.post(path, block)

    suspend fun put(
        path: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): HttpResponse = client.put(path, block)

    suspend fun patch(
        path: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): HttpResponse = client.patch(path, block)

    suspend fun delete(
        path: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): HttpResponse = client.delete(path, block)

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
                    prettyPrint = true
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
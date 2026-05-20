package com.cargo.driver.shared.data.remote.client

import com.cargo.driver.shared.data.local.datastore.TokenStorage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.plugin
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.Serializable

class CargoInterceptor(
    private val tokenStorage: TokenStorage
) {

    private val mutex = Mutex()

    fun install(client: HttpClient) {

        client.plugin(HttpSend).intercept { request ->

            val path = request.url.encodedPath

            val isPublic = path.contains("login") ||
                    path.contains("register") ||
                    path.contains("refresh-token") ||
                    path.contains("verify-email")

            if (isPublic) return@intercept execute(request)

            tokenStorage.getAccessToken()?.let { token ->
                request.headers.remove(HttpHeaders.Authorization)
                request.headers.append(HttpHeaders.Authorization, "Bearer $token")
            }

            val call = execute(request)

            if (call.response.status == HttpStatusCode.Unauthorized) {

                val newAccessToken = mutex.withLock {
                    val refreshToken = tokenStorage.getRefreshToken()
                        ?: return@withLock null

                    val refreshResponse = client.post {
                        url("refresh-token")
                        contentType(ContentType.Application.Json)
                        setBody(RefreshTokenRequest(refreshToken))
                    }

                    if (refreshResponse.status == HttpStatusCode.OK) {
                        val body = refreshResponse.body<RefreshTokenResponse>()
                        tokenStorage.saveAccessToken(body.accessToken)
                        body.refreshToken?.let { tokenStorage.saveRefreshToken(it) }
                        body.accessToken
                    } else {
                        tokenStorage.clearTokens()
                        null
                    }
                }

                if (!newAccessToken.isNullOrEmpty()) {
                    request.headers.remove(HttpHeaders.Authorization)
                    request.headers.append(HttpHeaders.Authorization, "Bearer $newAccessToken")
                    return@intercept execute(request)
                }
            }

            call
        }
    }
}

@Serializable
private data class RefreshTokenRequest(val refreshToken: String)

@Serializable
private data class RefreshTokenResponse(
    val accessToken: String,
    val refreshToken: String? = null
)
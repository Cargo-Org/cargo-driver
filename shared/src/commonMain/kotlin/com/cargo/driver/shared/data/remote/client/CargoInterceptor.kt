package com.cargo.driver.shared.data.remote.client

import com.cargo.driver.shared.data.local.datastore.TokenStorage
import com.cargo.driver.shared.data.remote.util.ApiConstants
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

    private val publicPaths = setOf(
        ApiConstants.LOGIN,
        ApiConstants.REGISTER,
        ApiConstants.REFRESH_TOKEN,
        ApiConstants.VERIFY_EMAIL
    )

    fun install(client: HttpClient) {

        client.plugin(HttpSend).intercept { request ->

            val path = request.url.encodedPath

            val isPublic = publicPaths.any { path.endsWith(it) }
            if (isPublic) return@intercept execute(request)

            val tokenUsed = tokenStorage.getAccessToken()

            if (tokenUsed != null) {
                request.headers.remove(HttpHeaders.Authorization)
                request.headers.append(HttpHeaders.Authorization, "Bearer $tokenUsed")
            }

            val call = execute(request)

            if (call.response.status != HttpStatusCode.Unauthorized) {
                return@intercept call
            }

            val newAccessToken = mutex.withLock {

                val currentToken = tokenStorage.getAccessToken()
                if (currentToken != null && currentToken != tokenUsed) {
                    return@withLock currentToken
                }

                val refreshToken = tokenStorage.getRefreshToken()
                    ?: run {
                        tokenStorage.clearTokens()
                        return@withLock null
                    }

                val refreshResponse = client.post {
                    url(ApiConstants.REFRESH_TOKEN)
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
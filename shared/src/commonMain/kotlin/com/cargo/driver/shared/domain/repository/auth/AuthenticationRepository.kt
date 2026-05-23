package com.cargo.driver.shared.domain.repository.auth

interface AuthenticationRepository {
    suspend fun saveAccessToken(token: String)
    suspend fun saveRefreshToken(token: String)

    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?

    suspend fun clearTokens()
}
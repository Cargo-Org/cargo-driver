package com.cargo.driver.shared.domain.repository.auth

import com.cargo.driver.shared.domain.model.register.RegisterRequest
import com.cargo.driver.shared.domain.model.register.RegisterResponse

interface AuthenticationRepository {
    suspend fun saveAccessToken(token: String)
    suspend fun saveRefreshToken(token: String)

    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?

    suspend fun clearTokens()

    suspend fun register(registerRequest: RegisterRequest) : RegisterResponse
}
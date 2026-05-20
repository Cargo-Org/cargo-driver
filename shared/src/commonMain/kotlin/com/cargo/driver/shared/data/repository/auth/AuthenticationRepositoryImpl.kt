package com.cargo.driver.shared.data.repository.auth

import com.cargo.driver.shared.data.local.datastore.TokenStorage
import com.cargo.driver.shared.data.remote.datasource.auth.AuthenticationRemoteDataSource
import com.cargo.driver.shared.domain.repository.auth.AuthenticationRepository

class AuthenticationRepositoryImpl(
    private val tokenStorage: TokenStorage,
    private val remote: AuthenticationRemoteDataSource,
    ) : AuthenticationRepository {

    override suspend fun saveAccessToken(token: String) {
        tokenStorage.saveAccessToken(token)
    }

    override suspend fun saveRefreshToken(token: String) {
        tokenStorage.saveRefreshToken(token)
    }

    override suspend fun getAccessToken(): String? {
        return tokenStorage.getAccessToken()
    }

    override suspend fun getRefreshToken(): String? {
        return tokenStorage.getRefreshToken()
    }

    override suspend fun clearTokens() {
        tokenStorage.clearTokens()
    }
}
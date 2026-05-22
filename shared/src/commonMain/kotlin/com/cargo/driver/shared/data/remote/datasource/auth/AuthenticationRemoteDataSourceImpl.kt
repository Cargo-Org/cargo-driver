package com.cargo.driver.shared.data.remote.datasource.auth

import com.cargo.driver.shared.data.local.datastore.TokenStorage
import com.cargo.driver.shared.data.remote.client.NetworkClient
import com.cargo.driver.shared.data.remote.dto.auth.LoginRequestDto
import com.cargo.driver.shared.data.remote.dto.auth.LoginResponseDto
import com.cargo.driver.shared.data.remote.dto.auth.ProfileResponseDto
import com.cargo.driver.shared.data.remote.util.safeApiCall
import com.cargo.driver.shared.domain.result.ApiResult
import io.ktor.client.request.setBody

class AuthenticationRemoteDataSourceImpl(
    private val networkClient: NetworkClient,
    private val tokenStorage: TokenStorage
) : AuthenticationRemoteDataSource {

    override suspend fun login(
        email: String,
        password: String
    ): ApiResult<LoginResponseDto> =
        safeApiCall<LoginResponseDto> {
            networkClient.post("login") {
                setBody(LoginRequestDto(email, password))
            }
        }.also { result ->
            if (result is ApiResult.Success) {
                tokenStorage.saveAccessToken(result.data.accessToken)
                tokenStorage.saveRefreshToken(result.data.refreshToken)
            }
        }

    override suspend fun getUserProfile(): ApiResult<ProfileResponseDto> =
        safeApiCall {
            networkClient.get("me")
        }
}
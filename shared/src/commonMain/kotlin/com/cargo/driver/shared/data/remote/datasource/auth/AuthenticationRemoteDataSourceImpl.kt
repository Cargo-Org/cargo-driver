package com.cargo.driver.shared.data.remote.datasource.auth

import com.cargo.driver.shared.data.remote.client.NetworkClient
import com.cargo.driver.shared.data.remote.dto.auth.LoginRequestDto
import com.cargo.driver.shared.data.remote.dto.auth.LoginResponseDto
import com.cargo.driver.shared.data.remote.dto.auth.ProfileResponseDto
import io.ktor.client.request.setBody

class AuthenticationRemoteDataSourceImpl(
    private val networkClient: NetworkClient
) : AuthenticationRemoteDataSource {

    override suspend fun login(email: String, password: String): LoginResponseDto =
        networkClient.post("login") {
            setBody(LoginRequestDto(email, password))
        }

    override suspend fun getUserProfile(): ProfileResponseDto =
        networkClient.get("me")
}
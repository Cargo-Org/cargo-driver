package com.cargo.driver.shared.data.remote.datasource.auth

import com.cargo.driver.shared.data.remote.dto.auth.LoginRequestDto
import com.cargo.driver.shared.data.remote.dto.auth.LoginResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AuthenticationRemoteDataSourceImp(
    private val client: HttpClient
) : AuthenticationRemoteDataSource {
    override suspend fun login(email: String, password: String): LoginResponseDto {
        return client.post("login") {
            setBody(LoginRequestDto(email, password))
        }.body()
    }
}
package com.cargo.driver.shared.data.remote.datasource.auth

import com.cargo.driver.shared.data.remote.dto.auth.LoginResponseDto

interface AuthenticationRemoteDataSource {
    suspend fun login(email: String, password: String): LoginResponseDto
}
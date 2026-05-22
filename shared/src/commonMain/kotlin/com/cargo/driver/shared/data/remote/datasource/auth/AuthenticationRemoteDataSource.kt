package com.cargo.driver.shared.data.remote.datasource.auth

import com.cargo.driver.shared.data.remote.dto.auth.LoginResponseDto
import com.cargo.driver.shared.data.remote.dto.auth.ProfileResponseDto

interface AuthenticationRemoteDataSource {
    suspend fun login(email: String, password: String): LoginResponseDto

    suspend fun getUserProfile(): ProfileResponseDto
}
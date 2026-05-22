package com.cargo.driver.shared.data.remote.datasource.auth

import com.cargo.driver.shared.data.remote.dto.auth.LoginResponseDto
import com.cargo.driver.shared.data.remote.dto.auth.ProfileResponseDto
import com.cargo.driver.shared.domain.result.ApiResult

interface AuthenticationRemoteDataSource {
    suspend fun login(email: String, password: String): ApiResult<LoginResponseDto>
    suspend fun getUserProfile(): ApiResult<ProfileResponseDto>
}
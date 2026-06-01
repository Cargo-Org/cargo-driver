package com.cargo.driver.shared.data.remote.datasource.auth

import com.cargo.driver.shared.data.remote.dto.register.RegisterRequestDTO
import com.cargo.driver.shared.data.remote.dto.register.RegisterResponseDTO
import com.cargo.driver.shared.domain.result.ApiResult


interface AuthenticationRemoteDataSource {
    suspend fun register(registerRequest: RegisterRequestDTO): ApiResult<RegisterResponseDTO>
}
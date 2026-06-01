package com.cargo.driver.shared.data.remote.datasource.auth

import com.cargo.driver.shared.data.local.datastore.TokenStorage
import com.cargo.driver.shared.data.remote.client.NetworkClient
import com.cargo.driver.shared.data.remote.dto.register.RegisterRequestDTO
import com.cargo.driver.shared.data.remote.dto.register.RegisterResponseDTO
import com.cargo.driver.shared.data.remote.util.safeApiCall
import com.cargo.driver.shared.domain.result.ApiResult
import io.ktor.client.request.setBody

class AuthenticationRemoteDataSourceImpl(
    private val networkClient: NetworkClient,
    private val tokenStorage: TokenStorage
) : AuthenticationRemoteDataSource {

    override suspend fun register(registerRequest: RegisterRequestDTO): ApiResult<RegisterResponseDTO> {
        return safeApiCall<RegisterResponseDTO> {
            networkClient.post("register", {
                this.setBody(
                    registerRequest
                )
            })
        }
    }
}
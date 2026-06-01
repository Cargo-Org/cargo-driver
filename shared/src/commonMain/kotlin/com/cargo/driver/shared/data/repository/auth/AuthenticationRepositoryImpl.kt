package com.cargo.driver.shared.data.repository.auth

import com.cargo.driver.shared.data.local.datastore.TokenStorage
import com.cargo.driver.shared.data.mapper.register.toDTO
import com.cargo.driver.shared.data.mapper.register.toRegisterError
import com.cargo.driver.shared.data.remote.datasource.auth.AuthenticationRemoteDataSource
import com.cargo.driver.shared.data.remote.dto.GeneralClientErrorDTO
import com.cargo.driver.shared.data.remote.dto.ValidationClientErrorDTO
import com.cargo.driver.shared.domain.exception.CargoException
import com.cargo.driver.shared.domain.exception.ClientErrorException
import com.cargo.driver.shared.domain.exception.NoInternetException
import com.cargo.driver.shared.domain.model.register.RegisterError
import com.cargo.driver.shared.domain.model.register.RegisterRequest
import com.cargo.driver.shared.domain.model.register.RegisterResponse
import com.cargo.driver.shared.domain.model.register.RegisterSuccess
import com.cargo.driver.shared.domain.repository.auth.AuthenticationRepository
import com.cargo.driver.shared.domain.result.ApiResult

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

    override suspend fun register(registerRequest: RegisterRequest): RegisterResponse {
        return when (val apiResult = remote.register(registerRequest.toDTO())) {
            is ApiResult.Success -> RegisterSuccess
            is ApiResult.Error -> mapRegisterError(apiResult.exception)
        }
    }

    private fun mapRegisterError(exception: CargoException): RegisterResponse {
        return when (exception) {
            is ClientErrorException -> {
                when (val error = exception.error) {
                    is ValidationClientErrorDTO -> error.toRegisterError()
                    is GeneralClientErrorDTO -> error.toRegisterError()
                }
            }
            is NoInternetException -> RegisterError.NoInternet
            else -> RegisterError.Unknown
        }
    }
}
package com.cargo.driver.shared.domain.usecase.register

import com.cargo.driver.shared.domain.model.register.RegisterRequest
import com.cargo.driver.shared.domain.model.register.RegisterResponse
import com.cargo.driver.shared.domain.repository.auth.AuthenticationRepository


class RegisterUseCase(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(registerRequest: RegisterRequest): RegisterResponse =
        repository.register(registerRequest)
}
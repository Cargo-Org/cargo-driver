package com.cargo.driver.shared.data.repository

import com.cargo.driver.shared.data.remote.datasource.AuthRemoteDataSource
import com.cargo.driver.shared.domain.repository.AuthRepository

class AuthRepositoryImp (
    private val remote: AuthRemoteDataSource,
): AuthRepository {
}
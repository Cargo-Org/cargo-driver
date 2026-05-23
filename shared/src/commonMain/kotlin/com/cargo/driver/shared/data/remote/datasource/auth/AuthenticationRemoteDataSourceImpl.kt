package com.cargo.driver.shared.data.remote.datasource.auth

import com.cargo.driver.shared.data.local.datastore.TokenStorage
import com.cargo.driver.shared.data.remote.client.NetworkClient

class AuthenticationRemoteDataSourceImpl(
    private val networkClient: NetworkClient,
    private val tokenStorage: TokenStorage
) : AuthenticationRemoteDataSource {

}
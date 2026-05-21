package com.cargo.driver.shared.di

import com.cargo.driver.shared.data.remote.datasource.auth.AuthenticationRemoteDataSource
import com.cargo.driver.shared.data.remote.datasource.auth.AuthenticationRemoteDataSourceImp
import com.cargo.driver.shared.data.repository.auth.AuthenticationRepositoryImpl
import com.cargo.driver.shared.domain.repository.auth.AuthenticationRepository
import org.koin.dsl.module


// Shared: repositories, use cases, shared ViewModels
val sharedModule = module {
    // provide repositories
    // provide use cases
    // provide shared ViewModels

    //datasource
    single<AuthenticationRemoteDataSource> {
        AuthenticationRemoteDataSourceImp(get())
    }

    //repo
    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(
            tokenStorage = get(),
            remote = get()
        )
    }
}
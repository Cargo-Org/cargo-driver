package com.cargo.driver.shared.di
import com.cargo.driver.shared.data.remote.datasource.AuthRemoteDataSource
import com.cargo.driver.shared.data.remote.datasource.AuthRemoteDataSourceImp
import com.cargo.driver.shared.data.repository.AuthRepositoryImp
import com.cargo.driver.shared.domain.repository.AuthRepository
import org.koin.dsl.module


// Shared: repositories, use cases, shared ViewModels
val sharedModule = module {
    // provide repositories
    // provide use cases
    // provide shared ViewModels

    //datasource
    single<AuthRemoteDataSource> {
        AuthRemoteDataSourceImp()
    }

    //repo
    single <AuthRepository> {
        AuthRepositoryImp(
            remote = get()
        )
    }
}
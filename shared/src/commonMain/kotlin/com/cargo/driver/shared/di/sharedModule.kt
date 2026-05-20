package com.cargo.driver.shared.di
import com.cargo.driver.shared.data.remote.datasource.AuthRemoteDataSource
import com.cargo.driver.shared.data.remote.datasource.AuthRemoteDataSourceImp
import com.cargo.driver.shared.data.repository.AuthRepositoryImp
import com.cargo.driver.shared.data.repository.UserPreferencesRepositoryImpl
import com.cargo.driver.shared.domain.repository.AuthRepository
import com.cargo.driver.shared.domain.repository.UserPreferencesRepository
import com.cargo.driver.shared.domain.usecase.onboarding.CompleteOnboardingUseCase
import com.cargo.driver.shared.domain.usecase.onboarding.GetOnboardingCompletedUseCase
import org.koin.dsl.module


// Shared: repositories, use cases, shared ViewModels
val sharedModule = module {

    // provide repositories
    single<UserPreferencesRepository> {
        UserPreferencesRepositoryImpl(
            dataStore = get()
        )
    }

    single <AuthRepository> {
        AuthRepositoryImp(
            remote = get()
        )
    }

    // provide use cases
    factory {
        CompleteOnboardingUseCase(
            repository = get()
        )
    }

    factory {
        GetOnboardingCompletedUseCase(
            repository = get()
        )
    }


    //datasource
    single<AuthRemoteDataSource> {
        AuthRemoteDataSourceImp()
    }


}
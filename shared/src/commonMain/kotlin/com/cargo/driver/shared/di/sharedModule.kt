package com.cargo.driver.shared.di

import com.cargo.driver.shared.data.remote.datasource.auth.AuthenticationRemoteDataSource
import com.cargo.driver.shared.data.remote.datasource.auth.AuthenticationRemoteDataSourceImpl
import com.cargo.driver.shared.data.repository.UserPreferencesRepositoryImpl
import com.cargo.driver.shared.data.repository.auth.AuthenticationRepositoryImpl
import com.cargo.driver.shared.domain.repository.UserPreferencesRepository
import com.cargo.driver.shared.domain.repository.auth.AuthenticationRepository
import com.cargo.driver.shared.domain.usecase.onboarding.CompleteOnboardingUseCase
import com.cargo.driver.shared.domain.usecase.onboarding.GetOnboardingCompletedUseCase
import com.cargo.driver.shared.domain.usecase.validation.ConfirmPasswordValidationUseCase
import com.cargo.driver.shared.domain.usecase.validation.EmailValidationUseCase
import com.cargo.driver.shared.domain.usecase.validation.FullNameValidationUseCase
import com.cargo.driver.shared.domain.usecase.validation.PasswordValidationUseCase
import com.cargo.driver.shared.domain.usecase.validation.PhoneValidationUseCase
import org.koin.dsl.module

// Shared: repositories, use cases, shared ViewModels
val sharedModule = module {

    //repository
    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(
            tokenStorage = get(),
            remote = get()
        )
    }

    single<UserPreferencesRepository> {
        UserPreferencesRepositoryImpl(
            dataStore = get()
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
    single<AuthenticationRemoteDataSource> {
        AuthenticationRemoteDataSourceImpl(get(), get())
    }


    // Validation UseCases
    factory {
        FullNameValidationUseCase()
    }

    factory {
        PhoneValidationUseCase()
    }

    factory {
        PasswordValidationUseCase()
    }

    factory {
        ConfirmPasswordValidationUseCase()
    }

    factory {
        EmailValidationUseCase()
    }
}
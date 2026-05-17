package com.cargo.driver.shared.di

import com.cargo.driver.shared.data.repository.UserPreferencesRepositoryImpl
import com.cargo.driver.shared.domain.repository.UserPreferencesRepository
import com.cargo.driver.shared.domain.usecase.onboarding.CompleteOnboardingUseCase
import com.cargo.driver.shared.domain.usecase.onboarding.ObserveOnboardingCompletedUseCase
import org.koin.dsl.module

val sharedModule = module {

    // Repositories
    single<UserPreferencesRepository> {
        UserPreferencesRepositoryImpl(
            userPreferencesLocalDataSource = get()
        )
    }

    // Use cases
    factory {
        CompleteOnboardingUseCase(
            repository = get()
        )
    }

    factory {
        ObserveOnboardingCompletedUseCase(
            repository = get()
        )
    }
}
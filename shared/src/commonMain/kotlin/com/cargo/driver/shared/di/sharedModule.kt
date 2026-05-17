package com.cargo.driver.shared.di
import com.cargo.driver.shared.data.repository.UserPreferencesRepositoryImpl
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

    // provide shared ViewModels
}
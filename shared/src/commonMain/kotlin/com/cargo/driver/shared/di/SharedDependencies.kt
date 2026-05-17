package com.cargo.driver.shared.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.cargo.driver.shared.data.local.datasource.preferences.UserPreferencesLocalDataSource
import com.cargo.driver.shared.data.local.datasource.preferences.UserPreferencesLocalDataSourceImpl
import com.cargo.driver.shared.data.repository.UserPreferencesRepositoryImpl
import com.cargo.driver.shared.domain.repository.UserPreferencesRepository
import com.cargo.driver.shared.domain.usecase.onboarding.CompleteOnboardingUseCase
import com.cargo.driver.shared.domain.usecase.onboarding.ObserveOnboardingCompletedUseCase

class SharedDependencies(
    dataStore: DataStore<Preferences>
) {
    private val userPreferencesLocalDataSource: UserPreferencesLocalDataSource =
        UserPreferencesLocalDataSourceImpl(
            dataStore = dataStore
        )

    private val userPreferencesRepository: UserPreferencesRepository =
        UserPreferencesRepositoryImpl(
            userPreferencesLocalDataSource = userPreferencesLocalDataSource
        )

    val observeOnboardingCompleted = ObserveOnboardingCompletedUseCase(
        repository = userPreferencesRepository
    )
    val completeOnboarding = CompleteOnboardingUseCase(
        repository = userPreferencesRepository
    )
}
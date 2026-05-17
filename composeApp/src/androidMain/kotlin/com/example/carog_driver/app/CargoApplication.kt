package com.example.carog_driver.app

import android.app.Application
import com.cargo.driver.shared.di.koinModule
import com.example.carog_driver.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class CargoApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CargoApplication)
            modules(koinModule + presentationModule)
        }
    }
}
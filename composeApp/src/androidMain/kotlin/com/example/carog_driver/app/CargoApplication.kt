package com.example.carog_driver.app

import android.app.Application
import com.example.carog_driver.initKoin
import org.koin.android.ext.koin.androidContext


class CargoApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@CargoApplication)
        }
    }
}
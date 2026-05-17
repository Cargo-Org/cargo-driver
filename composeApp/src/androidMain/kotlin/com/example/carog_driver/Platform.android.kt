package com.example.carog_driver

import android.os.Build
import com.cargo.driver.shared.di.androidModule
import com.cargo.driver.shared.di.sharedModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun initKoin(koinAppDeclaration: KoinAppDeclaration?){
    startKoin {
        androidLogger()
        koinAppDeclaration?.invoke(this)
        modules(androidModule)
    }
}
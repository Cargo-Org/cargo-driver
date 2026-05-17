package com.example.carog_driver

import com.cargo.driver.shared.di.iosModules
import com.cargo.driver.shared.di.sharedModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun initKoin(koinAppDeclaration: KoinAppDeclaration?){
    startKoin {
        koinAppDeclaration?.invoke(this)
        modules(iosModules)
    }
}
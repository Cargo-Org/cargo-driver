package com.example.carog_driver

import org.koin.dsl.KoinAppDeclaration

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun initKoin(koinAppDeclaration: KoinAppDeclaration? = null)
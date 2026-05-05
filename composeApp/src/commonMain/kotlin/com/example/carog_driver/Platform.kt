package com.example.carog_driver

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
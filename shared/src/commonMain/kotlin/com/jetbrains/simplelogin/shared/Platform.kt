package com.jetbrains.simplelogin.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
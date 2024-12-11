package com.example.akintern

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
package com.zoho.vtouch.testkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
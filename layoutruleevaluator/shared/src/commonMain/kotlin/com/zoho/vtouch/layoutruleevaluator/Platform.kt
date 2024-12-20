package com.zoho.vtouch.layoutruleevaluator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
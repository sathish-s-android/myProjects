package com.zoho.vtouch.layoutruleevaluator

actual class Test {
    actual fun convertFromMillisToISO8601(time: Long): String{
        return "123456"
    }

    actual fun convertFromISO8601ToMillis(time: String?): Long?{
        return 123L
    }

}

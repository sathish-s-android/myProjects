package com.zoho.vtouch.layoutruleevaluator

expect class Test {
    fun convertFromMillisToISO8601(time: Long): String

    fun convertFromISO8601ToMillis(time: String?): Long?

}

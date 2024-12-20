package com.zoho.vtouch.gsonandjson

import android.util.Log
import com.zoho.vtouch.gsonandjson.ui.theme.RulesConstants
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toInstant
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale


fun test1() {
// JSON string
    val json = "{\"name\":\"John\",\"age\":30}"

// Deserialize JSON string to Kotlin object
    val person: Person1 = Json.decodeFromString(json)

// Serialize Kotlin object to JSON string
    val jsonString: String = Json.encodeToString(person)


    Log.d("Sathish_SSS", "test 1: $json")
    Log.d("Sathish_SSS", "test 1: $person")

}

fun main2() {
    // Example JSON string
    val json = """{ "value": "someValue" }"""

    // Parse JSON string into a JsonObject
    val conditionObject = Json.parseToJsonElement(json).jsonObject

    // Access the 'value' field as a string
    val conditionValue = conditionObject[RulesConstants.VALUE]?.jsonPrimitive?.content
        ?: throw IllegalArgumentException("Missing or invalid 'value' field")

    Log.d("Sathish_SSS", "main2 : $conditionValue")//Output: someValue
}


fun testing(){

    val map = mutableMapOf<String,JsonElement>()
    val json = JsonObject(map)

    Log.d("Sathish_SSS", "testing: $json")

    map["sathish"] = JsonPrimitive("example")
    map["sathish 1"] = JsonPrimitive("example 1")
    map["sathish 2"] = JsonPrimitive("example 2")

    Log.d("Sathish_SSS 2", "testing: $json")
}

fun timeZone1(){
    val timeZoneInUse = TimeZone.currentSystemDefault()

    Log.d("Sathish_SSS", "timeZone1: $timeZoneInUse")
}

object LocalizationUtil1 {
     fun getUTCMillis(
        dateTime: String? = "1734590030190",
        defaultValue: Long = 0L
    ): Long {
        try {
            if (dateTime == null) return defaultValue
            val instant = getInstantFromEpochMillis(dateTime)
            return instant.toEpochMilliseconds()
        } catch (e: Exception) {
            return defaultValue
        }
    }
    private fun getInstantFromEpochMillis(epochMillis: String): Instant {
        return try {
            val millis = epochMillis.toLong()
            val instant = Instant.fromEpochMilliseconds(millis)

            instant
        } catch (e: Exception) {
            throw IllegalArgumentException("Invalid epoch milliseconds: $epochMillis", e)
        }
    }



    private fun getTimeZone(isDateTimeField: Boolean): TimeZone {
        return if (isDateTimeField) {
            TimeZone.currentSystemDefault() // Get the system default timezone
        } else {
            TimeZone.UTC // Use UTC as a fallback
        }
    }

//    "2024-08-03"

    fun getUTCMillisFromDateTimeString(dateTimeValue: String = "2024-12-19T15:30:45+01:00"): Long {
        return try {
            getUTCMillisFromAPIDateTimeString(dateTimeValue)
        } catch (e: Exception) {
            -1
        }
    }


    fun getUTCMillisFromAPIDateString(dateTimeValue: String): Long {
        return try {
            val localDate = LocalDate.parse(dateTimeValue)
            val instant = localDate.atStartOfDayIn(TimeZone.UTC)
            instant.toEpochMilliseconds()
        } catch (e: Exception) {
            -1L
        }
    }

    fun getUTCMillisFromAPIDateTimeString(dateTimeValue: String): Long {
        try {
            val instant = Instant.parse(dateTimeValue)
            return instant.toEpochMilliseconds()
        } catch (e: Exception) {
            return -1L
        }
    }

    val timeZoneInUse = TimeZone.currentSystemDefault()
    val TIME_ZONE_GMT = TimeZone.UTC
}



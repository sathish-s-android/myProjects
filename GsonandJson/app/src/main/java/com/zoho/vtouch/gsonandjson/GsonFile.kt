package com.zoho.vtouch.gsonandjson

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.zoho.vtouch.gsonandjson.ui.theme.ActionConstants
import com.zoho.vtouch.gsonandjson.ui.theme.LogicalOperators
import com.zoho.vtouch.gsonandjson.ui.theme.RulesConstants
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone


fun test() {
// JSON string
    val json = "{\"name\":\"John\",\"age\":30}"

// Deserialize JSON string to Kotlin object
    val person: Person = Gson().fromJson(json, Person::class.java)

// Serialize Kotlin object to JSON string
    val jsonString: String = Gson().toJson(person)


    Log.d("Sathish_SSS", "test: $json")
    Log.d("Sathish_SSS", "test: $person")

}

fun main1() {
    // Example JSON string
    val json = """{ "value": "someValue" }"""

    // Parse JSON string into a JsonObject
    val conditionObject: JsonObject = JsonParser.parseString(json).asJsonObject

    // Access the 'value' field as a string
    val conditionValue = conditionObject.get(RulesConstants.VALUE).asString

    Log.d("Sathish_SSS", "main1 : $conditionValue") // Output: someValue
}



object ConverterUtil1 {

    private var indexOfConditionExpression = -1

    fun convertConditionsToJsonArray(conditions: List<Condition>): JsonArray {
        val layoutRulesCore = JsonArray()
        var conditionJsonObject: JsonObject
        var criteriaJsonObject: JsonObject
        var actionsJsonArray: JsonArray
        for(condition in conditions) {
            conditionJsonObject = JsonObject()
            indexOfConditionExpression = 0
            if(condition.conditionPattern.startsWith('(') && condition.conditionPattern.endsWith(')')) {
                condition.conditionPattern = condition.conditionPattern.trim('(', ')')
            }
            criteriaJsonObject = convertCriteriaExpressionToJsonObject(condition.conditionPattern, condition.criteria)
            actionsJsonArray = convertActionToJsonArray(condition.actions)
            conditionJsonObject.add(RulesConstants.CRITERIA, criteriaJsonObject)
            conditionJsonObject.add(RulesConstants.ACTIONS, actionsJsonArray)
            layoutRulesCore.add(conditionJsonObject)
        }
        return layoutRulesCore
    }

    private fun convertCriteriaExpressionToJsonObject(conditionPattern: String, criteria: List<Criterion>): JsonObject {
        val criteriaJsonObject = JsonObject()
        val groupJsonArray = JsonArray()
        var criterionJsonObject: JsonObject
        var valuesJsonArray: JsonArray
        var criterion: Criterion
        var character: Char
        if((conditionPattern.isEmpty() || conditionPattern.length == 1) && criteria.size == 1) {
            criteriaJsonObject.addProperty(RulesConstants.COMPARATOR, criteria[0].criteriaCondition.name)
            criteriaJsonObject.addProperty(RulesConstants.FIELD, criteria[0].fieldId)
            if(criteria[0].values.size == 1) {
                criteriaJsonObject.addProperty(RulesConstants.VALUE, criteria[0].values[0])
            }
            else {
                valuesJsonArray = JsonArray()
                criteria[0].values.forEach {
                    valuesJsonArray.add(it)
                }
                criteriaJsonObject.add(RulesConstants.VALUE, valuesJsonArray)
            }
            return criteriaJsonObject
        }
        if(conditionPattern.isNotEmpty()) {
            while (indexOfConditionExpression < conditionPattern.length) {
                character = conditionPattern[indexOfConditionExpression]
                criterionJsonObject = JsonObject()
                if(character == ' ') {
                    indexOfConditionExpression++
                    continue
                }
                if(character.isDigit()) {
                    try {
                        criterion = criteria[character.digitToInt() - 1]
                        criterionJsonObject.addProperty(RulesConstants.COMPARATOR, criterion.criteriaCondition.name)
                        criterionJsonObject.addProperty(RulesConstants.FIELD, criterion.fieldId)
                        if(criterion.values.size == 1) {
                            criterionJsonObject.addProperty(RulesConstants.VALUE, criterion.values[0])
                        }
                        else {
                            valuesJsonArray = JsonArray()
                            criterion.values.forEach {
                                valuesJsonArray.add(it)
                            }
                            criterionJsonObject.add(RulesConstants.VALUE, valuesJsonArray)
                        }
                        groupJsonArray.add(criterionJsonObject)
                        indexOfConditionExpression++
                    } catch (exp: Exception) {
                        Log.d("Layout Rule Debug","Exception caught while converting character to digit -> Error Msg: ${exp.message}")
                        return JsonObject()
                    }
                }
                else if(character.isLetter()) {
                    if(conditionPattern.substring(indexOfConditionExpression, indexOfConditionExpression + 3).lowercase() == "and") {
                        criteriaJsonObject.addProperty(RulesConstants.GROUP_OPERATOR, LogicalOperators.AND)
                        indexOfConditionExpression += 3
                    }
                    else if(conditionPattern.substring(indexOfConditionExpression, indexOfConditionExpression + 2).lowercase() == "or") {
                        criteriaJsonObject.addProperty(RulesConstants.GROUP_OPERATOR, LogicalOperators.OR)
                        indexOfConditionExpression += 2
                    }
                }
                else if(character == '(') {
                    indexOfConditionExpression++
                    groupJsonArray.add(convertCriteriaExpressionToJsonObject(conditionPattern, criteria))
                }
                else if(character == ')') {
                    indexOfConditionExpression++
                    criteriaJsonObject.add(RulesConstants.GROUP, groupJsonArray)
                    return criteriaJsonObject
                }
            }
            criteriaJsonObject.add(RulesConstants.GROUP, groupJsonArray)
        }
        return criteriaJsonObject
    }

    private fun convertActionToJsonArray(actions: List<Action>): JsonArray {
        val actionsJsonArray = JsonArray()
        var fieldIdsJsonArray: JsonArray
        var actionJsonObject: JsonObject
        for(action in actions) {
            actionJsonObject = JsonObject()
            actionJsonObject.addProperty(RulesConstants.TYPE, action.actionType)
            fieldIdsJsonArray = JsonArray()
            action.fieldOrSectionIds.forEach {
                fieldIdsJsonArray.add(it)
            }
            val actionToBePerformedIn =
                if(action.actionType == ActionConstants.SHOW_SECTIONS) {
                    RulesConstants.SECTIONS
                }
                else {
                    RulesConstants.FIELDS
                }
            actionJsonObject.add(actionToBePerformedIn, fieldIdsJsonArray) // prvn sections ?
            actionsJsonArray.add(actionJsonObject)
        }
        return actionsJsonArray
    }
}



fun getCondition():List<Condition>{
   return listOf(
        Condition(
            conditionPattern = "1 AND (2 OR 3)",
            criteria = listOf(
                Criterion("001", LogicalOperators.Operator.EQUAL, listOf("12")),
                Criterion("002", LogicalOperators.Operator.EQUAL, listOf("34")),
                Criterion("003", LogicalOperators.Operator.EQUAL, listOf("56")),
            ),
            actions = listOf(Action(ActionConstants.SHOW_FIELDS, listOf("test value")))
        )
    )
}

fun timeZone2(){
    val timeZoneInUse = TimeZone.getDefault()

    Log.d("Sathish_SSS", "timeZone2: $timeZoneInUse")
}

object LocalizationUtil2 {
    fun getUTCMillis(
        dateTime: String? = "1734590030190",
        defaultValue: Long = 0L
    ): Long {
        val isDateTimeField: Boolean = true

        val dateCalendar: Calendar = Calendar.getInstance(getTimeZone(isDateTimeField))
        try {
            if (dateTime == null) return defaultValue
            dateCalendar.timeInMillis = dateTime.toLong()
            return dateCalendar.timeInMillis
        } catch (e: Exception) {
            Log.d("Layout Rule Debug", "Error Occurred in getUTCMillis -> Error msg: ${e.message}")
            return defaultValue
        }
    }

//"2024-08-03"
    fun getUTCMillisFromDateTimeString(dateTimeValue: String = "2024-12-19T15:30:45+01:00"): Long {
        return try {
            getUTCMillisFromAPIDateTimeString(dateTimeValue)
        } catch (e: Exception) {
            -1
        }
    }

    fun getUTCMillisFromAPIDateString(dateTimeValue: String): Long {
        try {
            val formatter = SimpleDateFormat(API_DATE_FORMAT, Locale.US)
            formatter.timeZone = TIME_ZONE_GMT
            return formatter.parse(dateTimeValue)?.time ?: -1
        } catch (e: Exception) {
            return -1
        }
    }

    fun getUTCMillisFromAPIDateTimeString(dateTimeValue: String): Long {
        try {
            val formatter = SimpleDateFormat(API_DATE_TIME_FORMAT, Locale.US)
            formatter.timeZone = TIME_ZONE_GMT
            return formatter.parse(dateTimeValue)?.time ?: -1
        } catch (e: java.lang.Exception) {

            return -1
        }
    }



    private fun getTimeZone(isDateTimeField: Boolean): TimeZone {
        return if(isDateTimeField) timeZoneInUse else TIME_ZONE_GMT
    }

    val timeZoneInUse = TimeZone.getDefault()
    val financialYearStartMonth = "april"
    val weekStartsWith = "sunday"
    val API_DATE_FORMAT = "yyyy-MM-dd"
    val TIME_ZONE_GMT = TimeZone.getTimeZone("GMT")
    val API_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZZZZ"
}

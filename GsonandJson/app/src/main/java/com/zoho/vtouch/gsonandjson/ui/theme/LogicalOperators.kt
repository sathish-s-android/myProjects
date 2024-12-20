package com.zoho.vtouch.gsonandjson.ui.theme

object LogicalOperators {

    enum class Operator {
        GREATER_THAN,
        GREATER_THAN_OR_EQUAL,
        LESS_THAN,
        LESS_THAN_OR_EQUAL,
        EQUAL,
        IS,
        IS_NOT,
        NOT_EQUAL,
        IS_EMPTY,
        IS_NOT_EMPTY,
        CONTAINS,
        NOT_CONTAINS,
        START_WITH,
        END_WITH,
        IS_UPDATED,
        LIKE,
        NOT_LIKE,
        BETWEEN,
        NOT_BETWEEN,
        YESTERDAY,
        TOMORROW,
        TODAY,
        TILL_YESTERDAY,
        UNSCHEDULED,
        NEXT_7_DAYS,
        LAST_MONTH,
        THIS_MONTH,
        NEXT_MONTH,
        LAST_WEEK,
        THIS_WEEK;

        companion object
        {
            fun toEnum(value: String): Operator
            {
                return Operator.values().first { it.name.equals(value, true) }
            }
        }
    }

    // $ operators
    const val NOT_EMPTY_PLACEHOLDER = "\${NOTEMPTY}"
    const val EMPTY_PLACEHOLDER = "\${EMPTY}"
    const val DEAL_OPEN = "\${OPEN}"
    const val DEAL_WON = "\${CLOSEDWON}"
    const val DEAL_LOST = "\${CLOSEDLOST}"

    const val CURRENT_USER = "\${CURRENTUSER}"

    // Date and date time operators
    const val PREVIOUS_FQ = "{PREVFQ}"
    const val THIS_FQ = "{THISFQ}"
    const val NEXT_FQ = "{NEXTFQ}"
    const val PREVIOUS_FY = "{PREVFY}"
    const val THIS_FY = "{THISFY}"
    const val NEXT_FY = "{NEXTFY}"
    const val TODAY = "{TODAY}"
    const val TOMORROW = "{TOMORROW}"
    const val YESTERDAY = "{YESTERDAY}"
    const val STARTING_TOMORROW = "{TOMORROWPLUS}"
    const val TILL_YESTERDAY = "{TILL_YESTERDAY}"
    const val UNSCHEDULED = "{UNSCHEDULED}"
    const val NEXT_7_DAYS = "{NEXT_7_DAYS}"
    const val LAST_MONTH = "{LAST_MONTH}"
    const val THIS_MONTH = "{THIS_MONTH}"
    const val NEXT_MONTH = "{NEXTMONTH}"
    const val LAST_WEEK = "{LAST_WEEK}"
    const val THIS_WEEK = "{THIS_WEEK}"
    const val NEXT_WEEK = "{NEXTWEEK}"
    const val LAST_YEAR = "{LASTYEAR}"
    const val THIS_YEAR = "{THISYEAR}"
    const val NEXT_YEAR = "{NEXTYEAR}"
    const val AGE_IN_DAYS = "{AGEINDAYS}"
    const val DUE_IN_DAYS = "{DUEINDAYS}"

    // logical operators
    const val AND = "and"
    const val OR = "or"
}
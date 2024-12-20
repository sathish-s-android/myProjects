package com.zoho.vtouch.gsonandjson

import com.zoho.vtouch.gsonandjson.ui.theme.LogicalOperators

data class Condition(
    var conditionPattern: String,
    val criteria: List<Criterion>,
    val actions: List<Action>
)

data class Criterion(
    val fieldId: String,
    val criteriaCondition: LogicalOperators.Operator,
    val values: List<String>
)

data class Action(
    val actionType: String,
    val fieldOrSectionIds: List<String>
)
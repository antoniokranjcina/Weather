package com.antoniok.core.model

data class ConditionForecast(
    val condition: String,
    val minTemperature: Int,
    val hours: List<HourInfo>
)

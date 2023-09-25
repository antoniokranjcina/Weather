package com.antoniok.core.model

data class ForecastDay(
    val day: Int,
    val chanceOfRain: Int,
    val maxTempC: Double,
    val maxTempF: Double,
    val minTempC: Double,
    val minTempF: Double,
    val minCondition: Condition,
    val maxCondition: Condition
)

val dummyDays = (0..7).map {
    ForecastDay(
        day = it,
        chanceOfRain = 60,
        maxTempC = 21.1,
        maxTempF = 22.1,
        minTempC = 24.1,
        minTempF = 53.1,
        minCondition = dummyCondition,
        maxCondition = dummyCondition
    )
}
package com.antoniok.core.model

data class Hour(
    val hour: Int,
    val isDay: Boolean,
    val feelsLikeC: Double,
    val tempC: Double,
    val chanceOfRain: Int,
    val condition: Condition
)

val dummyHours = (0..24).map {
    Hour(
        hour = it,
        isDay = true,
        feelsLikeC = 23.2,
        tempC = 12.2,
        chanceOfRain = 21,
        condition = dummyCondition
    )
}

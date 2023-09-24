package com.antoniok.core.model

data class DailyWeatherForecast(
    val day: Int,
    val chanceOfRain: Int,
    val minConditionImage: String,
    val maxConditionImage: String,
    val minTemp: Int,
    val maxTemp: Int
)

val dummyDailyWeatherForecasts = (1 until 7).map {
    DailyWeatherForecast(
        day = it,
        chanceOfRain = 75,
        minConditionImage = "",
        maxConditionImage = "",
        minTemp = 22,
        maxTemp = 30
    )
}
package com.antoniok.feature.home.data

data class DailyWeatherForecast(
    val day: String,
    val chanceOfRain: Int,
    val minConditionImage: String,
    val maxConditionImage: String,
    val minTemp: Int,
    val maxTemp: Int
)

val dummyDailyWeatherForecasts = (1 until 7).map { day ->
    DailyWeatherForecast(
        day = "Monday",
        chanceOfRain = 75,
        minConditionImage = "",
        maxConditionImage = "",
        minTemp = 22,
        maxTemp = 30
    )
}

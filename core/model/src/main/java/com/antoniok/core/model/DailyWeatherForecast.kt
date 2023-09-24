package com.antoniok.core.model

data class DailyWeatherForecast(
    val day: String,
    val chanceOfRain: Int,
    val minConditionImage: String,
    val maxConditionImage: String,
    val minTemp: Int,
    val maxTemp: Int
)
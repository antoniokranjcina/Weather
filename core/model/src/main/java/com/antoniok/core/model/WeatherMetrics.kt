package com.antoniok.core.model

data class WeatherMetrics(
    val uvIndex: String,
    val humidity: Double,
    val wind: Double,
    val sunrise: String,
    val sunset: String
)

val dummyWeatherMetrics = WeatherMetrics(
    uvIndex = "Low",
    humidity = 87.1,
    wind = 5.1,
    sunrise = "06:43",
    sunset = "18:52",
)

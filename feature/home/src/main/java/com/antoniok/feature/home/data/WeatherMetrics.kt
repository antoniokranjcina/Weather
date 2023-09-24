package com.antoniok.feature.home.data

data class WeatherMetrics(
    val uvIndex: String,
    val moisture: String,
    val wind: String,
    val sunrise: String,
    val sunset: String
)

val dummyWeatherMetrics = WeatherMetrics(
    uvIndex = "Low",
    moisture = "87",
    wind = "6",
    sunrise = "06:43",
    sunset = "18:52",
)

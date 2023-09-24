package com.antoniok.feature.home.data

data class CurrentWeather(
    val degrees: Int,
    val description: String,
    val descriptionImage: String,
    val city: String,
    val feelsLike: Int
)

val dummyCurrentWeather = CurrentWeather(
    degrees = 22,
    description = "Sunny",
    descriptionImage = "",
    city = "Zagreb",
    feelsLike = 20
)

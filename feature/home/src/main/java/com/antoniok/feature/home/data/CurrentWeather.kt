package com.antoniok.feature.home.data

data class CurrentWeather(
    val realTemp: Int,
    val description: String,
    val descriptionImage: String,
    val city: String,
    val feelsLikeTemp: Int
)

val dummyCurrentWeather = CurrentWeather(
    realTemp = 22,
    description = "Sunny",
    descriptionImage = "",
    city = "Zagreb",
    feelsLikeTemp = 20
)

package com.antoniok.core.model

data class CurrentWeather(
    val realTemp: Int,
    val description: String,
    val descriptionImage: String,
    val feelsLikeTemp: Int
)

val dummyCurrentWeather = CurrentWeather(
    realTemp = 22,
    description = "Sunny",
    descriptionImage = "",
    feelsLikeTemp = 20
)

package com.antoniok.core.model

data class CurrentWeather(
    val realTemp: Int,
    val description: String,
    val descriptionImage: String,
    val city: String,
    val feelsLikeTemp: Int
)

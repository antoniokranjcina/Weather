package com.antoniok.core.model

data class Current(
    val lastUpdatedEpoch: Int,
    val tempC: Double,
    val tempF: Double,
    val isDay: Int,
    val condition: Condition,
    val windMph: Double,
    val windKph: Double,
    val windDegree: Int,
    val windDir: String,
    val pressureMb: Double,
    val pressureIn: Double,
    val humidity: Int,
    val cloud: Int,
    val feelsLikeC: Double,
    val feelsLikeF: Double,
    val uv: Int
)

val dummyCurrent = Current(
    lastUpdatedEpoch = 1,
    tempC = 20.1,
    tempF = 50.2,
    isDay = 1,
    condition = dummyCondition,
    windMph = 100.2,
    windKph = 50.2,
    windDegree = 90,
    windDir = "south",
    pressureMb = 1.2,
    pressureIn = 12.2,
    humidity = 70,
    cloud = 10,
    feelsLikeC = 19.2,
    feelsLikeF = 50.9,
    uv = 1,
)

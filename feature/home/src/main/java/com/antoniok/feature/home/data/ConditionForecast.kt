package com.antoniok.feature.home.data

data class ConditionForecast(
    val condition: String,
    val minTemperature: Int,
    val hours: List<HourInfo>
)

data class HourInfo(
    val hour: Int,
    val image: String,
    val temp: Int,
    val chanceOfRain: Int
)

val dummyHourInfoList = (0 until 24).map { hour ->
    HourInfo(
        hour = hour,
        image = "",
        temp = 22,
        chanceOfRain = 82
    )
}

val dummyConditionForecast = ConditionForecast(
    condition = "Rainy",
    minTemperature = 10,
    hours = dummyHourInfoList
)

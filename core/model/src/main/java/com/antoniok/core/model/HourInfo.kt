package com.antoniok.core.model

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

package com.antoniok.core.model

data class Weather(
    val location: Location,
    val current: Current,
    val day: Day,
    val astro: Astro,
    val hours: List<Hour>,
    val days: List<ForecastDay>
)

val dummyWeather = Weather(
    location = dummyLocation,
    current = dummyCurrent,
    day = dummyDay,
    astro = dummyAstro,
    hours = dummyHours,
    days = dummyDays
)

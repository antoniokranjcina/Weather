package com.antoniok.core.model

data class Weather(
    val location: Location,
    val current: Current,
    val day: Day,
    val astro: Astro
)

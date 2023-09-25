package com.antoniok.core.model

data class Astro(
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val moonset: String
)

val dummyAstro = Astro(
    sunrise = "06:54",
    sunset = "18:53",
    moonrise = "18:54",
    moonset = "05:12",
)

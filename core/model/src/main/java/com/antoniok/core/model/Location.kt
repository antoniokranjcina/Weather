package com.antoniok.core.model

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val localtimeEpoch: Int
)

val dummyLocation = Location(
    name = "Zagreb",
    region = "Grad Zagreb",
    country = "Croatia",
    localtimeEpoch = 1
)

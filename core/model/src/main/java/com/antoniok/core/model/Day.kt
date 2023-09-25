package com.antoniok.core.model

data class Day(
    val day: Int,
    val maxTempC: Double,
    val maxTempF: Double,
    val minTempC: Double,
    val minTempF: Double,
    val condition: Condition
)

val dummyDay = Day(
    day = -1,
    maxTempC = 12.2,
    maxTempF = 50.2,
    minTempC = 11.1,
    minTempF = 50.1,
    condition = dummyCondition
)

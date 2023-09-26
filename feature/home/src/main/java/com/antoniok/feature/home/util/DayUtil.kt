package com.antoniok.feature.home.util

import com.antoniok.weather.feature.home.R

fun Int.toDay(): Int = when (this) {
    1 -> R.string.monday
    2 -> R.string.tuesday
    3 -> R.string.wednesday
    4 -> R.string.thursday
    5 -> R.string.friday
    6 -> R.string.saturday
    7 -> R.string.sunday
    else -> R.string.invalid
}

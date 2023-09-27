package com.antoniok.core.data_source.local.database.util

import java.util.Calendar

fun Int.getDayOfWeek(): Int {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this * 1000L

    val dayOfWeek = when (calendar.get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> 1
        Calendar.TUESDAY -> 2
        Calendar.WEDNESDAY -> 3
        Calendar.THURSDAY -> 4
        Calendar.FRIDAY -> 5
        Calendar.SATURDAY -> 6
        Calendar.SUNDAY -> 7
        else -> 0
    }

    return dayOfWeek
}

fun Int.getHour(): Int {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this.toLong() * 1000
    return calendar.get(Calendar.HOUR_OF_DAY)
}

package com.antoniok.core.ui.icon

import com.antoniok.core.ui.icon.condition.DayConditionIcons
import com.antoniok.core.ui.icon.condition.DayConditionIcons.dayIcons
import com.antoniok.core.ui.icon.condition.NightConditionIcons
import com.antoniok.core.ui.icon.condition.NightConditionIcons.nightIcons

object ConditionIcon {

    fun findWeatherIcon(code: Int, isDay: Boolean = true): Int {
        val (icons, default) = if (isDay) {
            dayIcons to DayConditionIcons.day113.first
        } else {
            nightIcons to NightConditionIcons.night113.first
        }

        val icon = icons.find {
            it.second == code
        }
        return icon?.first ?: default
    }

}

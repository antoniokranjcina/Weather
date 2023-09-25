package com.antoniok.core.data_source.local.entity.forecast

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antoniok.core.model.Condition
import com.antoniok.core.model.ForecastDay

@Entity
data class ForecastDayEntity(
    @PrimaryKey(autoGenerate = false)
    val forecastDayId: String,
    val id: String,
    val day: Int,
    val chanceOfRain: Int,
    val maxTempC: Double,
    val maxTempF: Double,
    val minTempC: Double,
    val minTempF: Double,
    val minConditionText: String,
    val minConditionIcon: String,
    val minConditionCode: Int,
    val maxConditionText: String,
    val maxConditionIcon: String,
    val maxConditionCode: Int
)

fun ForecastDayEntity.asExternalModule() = ForecastDay(
    day = day,
    chanceOfRain = chanceOfRain,
    maxTempC = maxTempC,
    maxTempF = minTempF,
    minTempC = minTempC,
    minTempF = minTempF,
    minCondition = Condition(
        text = minConditionText,
        icon = minConditionIcon,
        code = minConditionCode
    ),
    maxCondition = Condition(
        text = maxConditionText,
        icon = maxConditionIcon,
        code = maxConditionCode
    )
)

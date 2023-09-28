package com.antoniok.core.data_source.local.database.entity.forecast

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antoniok.core.data_source.local.database.util.Table
import com.antoniok.core.model.Condition
import com.antoniok.core.model.Hour

@Entity(tableName = Table.HOUR)
data class HourEntity(
    @PrimaryKey(autoGenerate = false)
    val hourId: String,
    val id: String,
    val isDay: Boolean,
    val hour: Int,
    val feelsLikeC: Double,
    val tempC: Double,
    val chanceOfRain: Int,
    val conditionText: String,
    val conditionIcon: String,
    val conditionCode: Int
)

fun HourEntity.asExternalModule() = Hour(
    hour = hour,
    isDay = isDay,
    feelsLikeC = feelsLikeC,
    tempC = tempC,
    chanceOfRain = chanceOfRain,
    condition = Condition(
        text = conditionText,
        icon = conditionIcon,
        code = conditionCode
    )
)

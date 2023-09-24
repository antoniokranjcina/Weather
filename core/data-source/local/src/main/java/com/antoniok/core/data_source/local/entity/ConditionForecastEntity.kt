package com.antoniok.core.data_source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antoniok.core.data_source.local.util.Table

@Entity(tableName = Table.CONDITION_FORECAST)
data class ConditionForecastEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val condition: String,
    val minTemperature: Int
)

@Entity(tableName = Table.HOUR_INFO)
data class HourInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val hour: Int,
    val image: String,
    val temp: Int,
    val chanceOfRain: Int
)

data class ConditionForecastWithHours(
    val conditionForecast: ConditionForecastEntity,
    val hoursInfo: List<HourInfoEntity>
)

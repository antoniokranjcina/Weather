package com.antoniok.core.data_source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antoniok.core.data_source.local.util.Table

@Entity(tableName = Table.DAILY_WEATHER_FORECAST)
data class DailyWeatherForecastEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val day: Int,
    val chanceOfRain: Int,
    val minConditionImage: String,
    val maxConditionImage: String,
    val minTemp: Int,
    val maxTemp: Int
)

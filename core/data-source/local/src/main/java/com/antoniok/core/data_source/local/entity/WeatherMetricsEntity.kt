package com.antoniok.core.data_source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antoniok.core.data_source.local.util.Table

@Entity(tableName = Table.WEATHER_METRICS)
data class WeatherMetricsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val uvIndex: String,
    val humidity: Double,
    val wind: Double,
    val sunrise: String,
    val sunset: String
)

package com.antoniok.core.data_source.local.entity.forecast

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HourEntity(
    @PrimaryKey(autoGenerate = true)
    val hourId: Int = 0,
    val feelsLikeC: Double,
    val tempC: Double,
    val chanceOfRain: Double,
    val conditionText: String,
    val conditionIcon: String,
    val conditionCode: String
)

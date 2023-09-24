package com.antoniok.core.data_source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antoniok.core.data_source.local.util.Table

@Entity(tableName = Table.CURRENT_WEATHER)
data class CurrentWeatherEntity(
    @PrimaryKey(autoGenerate = false)
    val realTemperature: Int,
    val description: String,
    val descriptionImage: String,
    val city: String,
    val feelsLikeTemperature: Int
)

package com.antoniok.core.data_source.local.entity.forecast

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antoniok.core.model.Astro

@Entity
data class AstroEntity(
    @PrimaryKey(autoGenerate = true)
    val astroId: Int = 0,
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val moonset: String
)

fun AstroEntity.asExternalModel() = Astro(
    sunrise = sunrise,
    sunset = sunset,
    moonrise = moonrise,
    moonset = moonset
)

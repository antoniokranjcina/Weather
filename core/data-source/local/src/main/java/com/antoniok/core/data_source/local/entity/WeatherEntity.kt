package com.antoniok.core.data_source.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antoniok.core.data_source.local.entity.current.CurrentEntity
import com.antoniok.core.data_source.local.entity.current.asExternalModule
import com.antoniok.core.data_source.local.entity.forecast.AstroEntity
import com.antoniok.core.data_source.local.entity.forecast.DayEntity
import com.antoniok.core.data_source.local.entity.forecast.asExternalModel
import com.antoniok.core.data_source.local.entity.forecast.asExternalModule
import com.antoniok.core.data_source.local.entity.location.LocationEntity
import com.antoniok.core.data_source.local.entity.location.asExternalModule
import com.antoniok.core.model.Weather

@Entity
data class WeatherEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @Embedded val location: LocationEntity,
    @Embedded val current: CurrentEntity,
    @Embedded val day: DayEntity,
    @Embedded val astro: AstroEntity
)

fun WeatherEntity.asExternalModule() = Weather(
    location = location.asExternalModule(),
    current = current.asExternalModule(),
    day = day.asExternalModule(),
    astro = astro.asExternalModel()
)

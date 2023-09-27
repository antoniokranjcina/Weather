package com.antoniok.core.data_source.local.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.antoniok.core.data_source.local.database.entity.current.asExternalModule
import com.antoniok.core.data_source.local.database.entity.forecast.ForecastDayEntity
import com.antoniok.core.data_source.local.database.entity.forecast.HourEntity
import com.antoniok.core.data_source.local.database.entity.forecast.asExternalModel
import com.antoniok.core.data_source.local.database.entity.forecast.asExternalModule
import com.antoniok.core.data_source.local.database.entity.location.asExternalModule
import com.antoniok.core.model.Weather

data class WeatherWithDaysAndHours(
    @Embedded val weather: WeatherEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
    )
    val hours: List<HourEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
    )
    val forecastDays: List<ForecastDayEntity>
)

fun WeatherWithDaysAndHours.asExternalModule() = Weather(
    location = weather.location.asExternalModule(),
    current = weather.current.asExternalModule(),
    day = weather.day.asExternalModule(),
    astro = weather.astro.asExternalModel(),
    hours = hours.map(HourEntity::asExternalModule),
    days = forecastDays.map(ForecastDayEntity::asExternalModule)
)

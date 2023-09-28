package com.antoniok.weather.data_source.remote.model

import com.antoniok.core.data_source.local.database.entity.WeatherEntity
import com.antoniok.weather.data_source.remote.model.alerts.AlertsDto
import com.antoniok.weather.data_source.remote.model.current.CurrentDto
import com.antoniok.weather.data_source.remote.model.current.asEntity
import com.antoniok.weather.data_source.remote.model.forecast.ForecastDto
import com.antoniok.weather.data_source.remote.model.forecast.asEntity
import com.antoniok.weather.data_source.remote.model.location.LocationDto
import com.antoniok.weather.data_source.remote.model.location.asEntity

data class WeatherDto(
    val location: LocationDto,
    val current: CurrentDto,
    val forecast: ForecastDto,
    val alerts: AlertsDto
)

fun WeatherDto.asEntity() = WeatherEntity(
    id = location.name,
    location = location.asEntity(),
    current = current.asEntity(),
    day = forecast.forecastDay.first().day.asEntity(),
    astro = forecast.forecastDay.first().astro.asEntity()
)

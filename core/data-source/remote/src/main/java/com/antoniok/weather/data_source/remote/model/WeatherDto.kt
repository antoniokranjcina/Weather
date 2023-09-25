package com.antoniok.weather.data_source.remote.model

import com.antoniok.weather.data_source.remote.model.alerts.AlertsDto
import com.antoniok.weather.data_source.remote.model.current.CurrentDto
import com.antoniok.weather.data_source.remote.model.forecast.ForecastDto
import com.antoniok.weather.data_source.remote.model.location.LocationDto

data class WeatherDto(
    val location: LocationDto,
    val current: CurrentDto,
    val forecast: ForecastDto,
    val alerts: AlertsDto
)

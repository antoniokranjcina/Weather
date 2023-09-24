package com.antoniok.core.data_source.local.mapper

import com.antoniok.core.data_source.local.entity.WeatherMetricsEntity
import com.antoniok.core.model.WeatherMetrics

fun WeatherMetricsEntity.asExternalModel() = WeatherMetrics(
    uvIndex = this.uvIndex,
    humidity = this.humidity,
    wind = this.wind,
    sunrise = this.sunrise,
    sunset = this.sunset,
)

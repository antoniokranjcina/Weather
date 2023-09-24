package com.antoniok.core.data_source.local.mapper

import com.antoniok.core.data_source.local.entity.DailyWeatherForecastEntity
import com.antoniok.core.model.DailyWeatherForecast

fun DailyWeatherForecastEntity.asExternalModel() = DailyWeatherForecast(
    day = this.day,
    chanceOfRain = this.chanceOfRain,
    minConditionImage = this.minConditionImage,
    maxConditionImage = this.maxConditionImage,
    minTemp = this.minTemp,
    maxTemp = this.maxTemp,
)

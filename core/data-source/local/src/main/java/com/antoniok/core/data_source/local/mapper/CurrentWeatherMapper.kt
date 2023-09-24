package com.antoniok.core.data_source.local.mapper

import com.antoniok.core.data_source.local.entity.CurrentWeatherEntity
import com.antoniok.core.model.CurrentWeather

fun CurrentWeatherEntity.asExternalModel() = CurrentWeather(
    realTemp = this.realTemperature,
    description = this.description,
    descriptionImage = this.descriptionImage,
    feelsLikeTemp = this.feelsLikeTemperature
)

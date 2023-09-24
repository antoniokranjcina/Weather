package com.antoniok.core.data_source.local.mapper

import com.antoniok.core.data_source.local.entity.ConditionForecastWithHours
import com.antoniok.core.data_source.local.entity.HourInfoEntity
import com.antoniok.core.model.ConditionForecast
import com.antoniok.core.model.HourInfo


fun HourInfoEntity.asExternalModel() = HourInfo(
    hour = this.hour,
    image = this.image,
    temp = this.temp,
    chanceOfRain = this.chanceOfRain
)

fun ConditionForecastWithHours.asExternalModel() = ConditionForecast(
    condition = this.conditionForecast.condition,
    minTemperature = this.conditionForecast.minTemperature,
    hours = this.hoursInfo.map(HourInfoEntity::asExternalModel)
)

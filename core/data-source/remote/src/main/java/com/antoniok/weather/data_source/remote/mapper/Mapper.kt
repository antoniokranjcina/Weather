package com.antoniok.weather.data_source.remote.mapper

import com.antoniok.core.data_source.local.entity.ConditionForecastEntity
import com.antoniok.core.data_source.local.entity.CurrentWeatherEntity
import com.antoniok.core.data_source.local.entity.DailyWeatherForecastEntity
import com.antoniok.core.data_source.local.entity.HourInfoEntity
import com.antoniok.core.data_source.local.entity.WeatherMetricsEntity
import com.antoniok.core.data_source.local.util.getDayOfWeek
import com.antoniok.core.data_source.local.util.getHour
import com.antoniok.weather.data_source.remote.model.current.CurrentDto
import com.antoniok.weather.data_source.remote.model.forecast.ForecastDayDto
import com.antoniok.weather.data_source.remote.model.forecast.HourDto

fun ForecastDayDto.asWeatherMetricsEntity() = WeatherMetricsEntity(
    uvIndex = day.uv.toString(),
    humidity = day.avgHumidity,
    wind = day.maxWindKph,
    sunrise = astro.sunrise,
    sunset = astro.sunset,
)

fun ForecastDayDto.asDailyWeatherForecastEntity() = DailyWeatherForecastEntity(
    day = this.dateEpoch.getDayOfWeek(),
    chanceOfRain = this.day.dailyChanceOfRain,
    minConditionImage = this.hour.minBy { it.condition.code }.condition.icon,
    maxConditionImage = this.hour.maxBy { it.condition.code }.condition.icon,
    minTemp = this.day.minTempC.toInt(),
    maxTemp = this.day.maxTempC.toInt()
)

fun CurrentDto.asCurrentWeatherEntity() = CurrentWeatherEntity(
    realTemperature = this.tempC.toInt(),
    description = this.condition.text,
    descriptionImage = this.condition.icon,
    feelsLikeTemperature = this.feelsLikeC.toInt(),
)

fun ForecastDayDto.asConditionForecastEntity() = ConditionForecastEntity(
    condition = this.day.condition.text,
    minTemperature = this.day.minTempC.toInt(),
)

fun HourDto.asHourInfoEntity() = HourInfoEntity(
    hour = this.timeEpoch.getHour(),
    image = this.condition.icon,
    temp = this.tempC.toInt(),
    chanceOfRain = this.chanceOfRain,
)

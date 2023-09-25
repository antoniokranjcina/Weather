package com.antoniok.weather.data_source.remote.model.forecast

import com.antoniok.core.data_source.local.entity.forecast.ForecastDayEntity
import com.antoniok.core.data_source.local.util.getDayOfWeek
import com.google.gson.annotations.SerializedName

data class ForecastDayDto(
    @SerializedName("date") val date: String,
    @SerializedName("date_epoch") val dateEpoch: Int,
    @SerializedName("day") val day: DayDto,
    @SerializedName("astro") val astro: AstroDto,
    @SerializedName("hour") val hour: List<HourDto>
)

fun ForecastDayDto.asEntity(id: String) = ForecastDayEntity(
    forecastDayId = "$id$day",
    id = id,
    day = dateEpoch.getDayOfWeek(),
    chanceOfRain = day.dailyChanceOfRain,
    maxTempC = day.maxTempC,
    maxTempF = day.maxTempF,
    minTempC = day.minTempC,
    minTempF = day.minTempF,
    minConditionText = this.hour.minBy { it.condition.code }.condition.icon,
    minConditionIcon = this.hour.minBy { it.condition.code }.condition.icon,
    minConditionCode = this.hour.minBy { it.condition.code }.condition.code,
    maxConditionText = this.hour.maxBy { it.condition.code }.condition.icon,
    maxConditionIcon = this.hour.maxBy { it.condition.code }.condition.icon,
    maxConditionCode = this.hour.maxBy { it.condition.code }.condition.code,
)

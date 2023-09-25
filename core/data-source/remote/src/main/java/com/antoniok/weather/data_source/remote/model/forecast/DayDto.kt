package com.antoniok.weather.data_source.remote.model.forecast

import com.antoniok.core.data_source.local.entity.forecast.DayEntity
import com.antoniok.weather.data_source.remote.model.shared.AirQualityDto
import com.antoniok.weather.data_source.remote.model.shared.ConditionDto
import com.google.gson.annotations.SerializedName

data class DayDto(
    @SerializedName("maxtemp_c") val maxTempC: Double,
    @SerializedName("maxtemp_f") val maxTempF: Double,
    @SerializedName("mintemp_c") val minTempC: Double,
    @SerializedName("mintemp_f") val minTempF: Double,
    @SerializedName("avgtemp_c") val avgTempC: Double,
    @SerializedName("avgtemp_f") val avgTempF: Double,
    @SerializedName("maxwind_mph") val maxWindMph: Double,
    @SerializedName("maxwind_kph") val maxWindKph: Double,
    @SerializedName("totalprecip_mm") val totalPrecipMm: Double,
    @SerializedName("totalprecip_in") val totalPrecipIn: Double,
    @SerializedName("totalsnow_cm") val totalSnowCm: Int,
    @SerializedName("avgvis_km") val avgVisKm: Double,
    @SerializedName("avgvis_miles") val avgVisMiles: Double,
    @SerializedName("avghumidity") val avgHumidity: Double,
    @SerializedName("daily_will_it_rain") val dailyWillItRain: Int,
    @SerializedName("daily_chance_of_rain") val dailyChanceOfRain: Int,
    @SerializedName("daily_will_it_snow") val dailyWillItSnow: Int,
    @SerializedName("daily_chance_of_snow") val dailyChanceOfSnow: Int,
    @SerializedName("condition") val condition: ConditionDto,
    @SerializedName("uv") val uv: Int,
    @SerializedName("air_quality") val airQuality: AirQualityDto
)

fun DayDto.asEntity() = DayEntity(
    maxTempC = maxTempC,
    maxTempF = maxTempF,
    minTempC = minTempC,
    minTempF = minTempF,
    conditionText = condition.text,
    conditionIcon = condition.icon.substring(2),
    conditionCode = condition.code,
)

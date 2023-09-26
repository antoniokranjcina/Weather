package com.antoniok.weather.data_source.remote.model.forecast

import com.antoniok.core.data_source.local.entity.forecast.HourEntity
import com.antoniok.core.data_source.local.util.getHour
import com.antoniok.weather.data_source.remote.model.shared.AirQualityDto
import com.antoniok.weather.data_source.remote.model.shared.ConditionDto
import com.google.gson.annotations.SerializedName

data class HourDto(
    @SerializedName("time_epoch") val timeEpoch: Int,
    @SerializedName("time") val time: String,
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("temp_f") val tempF: Double,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("condition") val condition: ConditionDto,
    @SerializedName("wind_mph") val windMph: Double,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("wind_degree") val windDegree: Int,
    @SerializedName("wind_dir") val windDir: String,
    @SerializedName("pressure_mb") val pressureMb: Double,
    @SerializedName("pressure_in") val pressureIn: Double,
    @SerializedName("precip_mm") val precipMm: Double,
    @SerializedName("precip_in") val precipIn: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("cloud") val cloud: Int,
    @SerializedName("feelslike_c") val feelsLikeC: Double,
    @SerializedName("feelslike_f") val feelsLikeF: Double,
    @SerializedName("windchill_c") val windchillC: Double,
    @SerializedName("windchill_f") val windchillF: Double,
    @SerializedName("heatindex_c") val heatIndexC: Double,
    @SerializedName("heatindex_f") val heatIndexF: Double,
    @SerializedName("dewpoint_c") val dewPointC: Double,
    @SerializedName("dewpoint_f") val dewPointF: Double,
    @SerializedName("will_it_rain") val willItRain: Int,
    @SerializedName("chance_of_rain") val chanceOfRain: Int,
    @SerializedName("will_it_snow") val willItSnow: Int,
    @SerializedName("chance_of_snow") val chanceOfSnow: Int,
    @SerializedName("vis_km") val visKm: Double,
    @SerializedName("vis_miles") val visMiles: Double,
    @SerializedName("gust_mph") val gustMph: Double,
    @SerializedName("gust_kph") val gustKph: Double,
    @SerializedName("uv") val uv: Int,
    @SerializedName("air_quality") val airQuality: AirQualityDto
)

fun HourDto.asEntity(id: String) = HourEntity(
    hourId = "$id${timeEpoch.getHour()}",
    id = id,
    isDay = isDay != 0,
    hour = timeEpoch.getHour(),
    feelsLikeC = feelsLikeC,
    tempC = tempC,
    chanceOfRain = chanceOfRain,
    conditionText = condition.text,
    conditionIcon = condition.icon,
    conditionCode = condition.code,
)

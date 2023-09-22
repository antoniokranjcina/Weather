package com.antoniok.weather.data_source.remote.model.current

import com.antoniok.weather.data_source.remote.model.shared.AirQualityDto
import com.antoniok.weather.data_source.remote.model.shared.ConditionDto
import com.google.gson.annotations.SerializedName

data class CurrentDto(
    @SerializedName("last_updated_epoch") val lastUpdatedEpoch: Int,
    @SerializedName("last_updated") val lastUpdated: String,
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
    @SerializedName("vis_km") val visKm: Int,
    @SerializedName("vis_miles") val visMiles: Int,
    @SerializedName("uv") val uv: Int,
    @SerializedName("gust_mph") val gustMph: Double,
    @SerializedName("gust_kph") val gustKph: Double,
    @SerializedName("air_quality") val airQuality: AirQualityDto
)

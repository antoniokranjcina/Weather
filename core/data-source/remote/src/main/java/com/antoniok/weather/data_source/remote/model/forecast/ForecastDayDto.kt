package com.antoniok.weather.data_source.remote.model.forecast

import com.google.gson.annotations.SerializedName

data class ForecastDayDto(
    @SerializedName("date") val date: String,
    @SerializedName("date_epoch") val dateEpoch: Int,
    @SerializedName("day") val day: DayDto,
    @SerializedName("astro") val astro: AstroDto,
    @SerializedName("hour") val hour: List<HourDto>
)

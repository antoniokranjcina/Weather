package com.antoniok.weather.data_source.remote.model.forecast

import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("forecastday") val forecastDay: List<ForecastDayDto>
)

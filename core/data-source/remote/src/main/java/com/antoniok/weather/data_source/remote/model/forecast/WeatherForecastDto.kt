package com.antoniok.weather.data_source.remote.model.forecast

import com.google.gson.annotations.SerializedName

data class WeatherForecastDto(
    @SerializedName("forecast") val forecast: ForecastDto
)

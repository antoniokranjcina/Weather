package com.antoniok.weather.data_source.remote.model.alerts

import com.google.gson.annotations.SerializedName

internal data class WeatherAlertDto(
    @SerializedName("alerts") val alertDto: AlertsDto
)

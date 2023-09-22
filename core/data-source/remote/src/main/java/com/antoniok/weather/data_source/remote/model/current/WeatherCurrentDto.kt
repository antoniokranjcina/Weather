package com.antoniok.weather.data_source.remote.model.current

import com.google.gson.annotations.SerializedName

internal data class WeatherCurrentDto(
    @SerializedName("current") val current: CurrentDto
)

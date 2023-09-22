package com.antoniok.weather.data_source.remote.model.location

import com.google.gson.annotations.SerializedName

data class WeatherLocationDto(
    @SerializedName("location") val location: LocationDto
)

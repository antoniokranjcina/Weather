package com.antoniok.weather.data_source.remote.model.location

import com.antoniok.core.model.SearchedLocation
import com.google.gson.annotations.SerializedName


data class SearchedLocationDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("country") val country: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
    @SerializedName("url") val url: String
)

fun SearchedLocationDto.asExternalModel() = SearchedLocation(
    id = id,
    name = name,
    region = region,
    country = country,
    lat = lat,
    lon = lon,
    url = url
)

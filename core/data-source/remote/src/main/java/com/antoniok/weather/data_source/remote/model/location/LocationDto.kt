package com.antoniok.weather.data_source.remote.model.location

import com.antoniok.core.data_source.local.database.entity.location.LocationEntity
import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("country") val country: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
    @SerializedName("tz_id") val tzId: String,
    @SerializedName("localtime_epoch") val localtimeEpoch: Int,
    @SerializedName("localtime") val localtime: String
)

fun LocationDto.asEntity() = LocationEntity(
    name = name,
    region = region,
    country = country,
    localtimeEpoch = localtimeEpoch
)

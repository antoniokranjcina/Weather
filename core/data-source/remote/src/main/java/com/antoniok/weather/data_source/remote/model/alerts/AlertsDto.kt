package com.antoniok.weather.data_source.remote.model.alerts

import com.google.gson.annotations.SerializedName

data class AlertsDto(
    @SerializedName("alert") val alerts: List<AlertDto>
)

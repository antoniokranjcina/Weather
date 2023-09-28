package com.antoniok.weather.data_source.remote.model.shared

import com.antoniok.core.data_source.local.database.entity.shared.ConditionEntity
import com.google.gson.annotations.SerializedName

data class ConditionDto(
    @SerializedName("text") val text: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("code") val code: Int
)

fun ConditionDto.asEntity() = ConditionEntity(
    text = text,
    icon = icon.substring(2),
    code = code
)

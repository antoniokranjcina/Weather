package com.antoniok.core.data_source.local.entity.current

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antoniok.core.data_source.local.entity.shared.ConditionEntity
import com.antoniok.core.data_source.local.entity.shared.asExternalModule
import com.antoniok.core.data_source.local.util.Table
import com.antoniok.core.model.Current

@Entity(tableName = Table.CURRENT)
data class CurrentEntity(
    @PrimaryKey(autoGenerate = true)
    val currentId: Int = 0,
    val lastUpdatedEpoch: Int,
    val tempC: Double,
    val tempF: Double,
    val isDay: Int,
    @Embedded val condition: ConditionEntity,
    val windMph: Double,
    val windKph: Double,
    val windDegree: Int,
    val windDir: String,
    val pressureMb: Double,
    val pressureIn: Double,
    val humidity: Int,
    val cloud: Int,
    val feelsLikeC: Double,
    val feelsLikeF: Double,
    val uv: Int,
)

fun CurrentEntity.asExternalModule() = Current(
    lastUpdatedEpoch = lastUpdatedEpoch,
    tempC = tempC,
    tempF = tempF,
    isDay = isDay,
    condition = condition.asExternalModule(),
    windMph = windMph,
    windKph = windKph,
    windDegree = windDegree,
    windDir = windDir,
    pressureMb = pressureMb,
    pressureIn = pressureIn,
    humidity = humidity,
    cloud = cloud,
    feelsLikeC = feelsLikeC,
    feelsLikeF = feelsLikeF,
    uv = uv
)

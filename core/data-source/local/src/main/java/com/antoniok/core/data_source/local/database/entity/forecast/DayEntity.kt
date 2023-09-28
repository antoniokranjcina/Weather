package com.antoniok.core.data_source.local.database.entity.forecast

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antoniok.core.data_source.local.database.util.Table
import com.antoniok.core.model.Condition
import com.antoniok.core.model.Day

@Entity(tableName = Table.DAY)
data class DayEntity(
    @PrimaryKey(autoGenerate = true)
    val dayId: Int = 0,
    val maxTempC: Double,
    val maxTempF: Double,
    val minTempC: Double,
    val minTempF: Double,
    val conditionText: String,
    val conditionIcon: String,
    val conditionCode: Int
)

fun DayEntity.asExternalModule() = Day(
    day = -1,
    maxTempC = maxTempC,
    maxTempF = maxTempF,
    minTempC = minTempC,
    minTempF = minTempF,
    condition = Condition(
        text = conditionText,
        icon = conditionIcon,
        code = conditionCode
    )
)

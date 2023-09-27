package com.antoniok.core.data_source.local.database.entity.shared

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antoniok.core.data_source.local.database.util.Table
import com.antoniok.core.model.Condition

@Entity(tableName = Table.CONDITION)
data class ConditionEntity(
    @PrimaryKey(autoGenerate = true)
    val conditionId: Int = 0,
    val text: String,
    val icon: String,
    val code: Int
)

fun ConditionEntity.asExternalModule() = Condition(
    text = text,
    icon = icon,
    code = code
)

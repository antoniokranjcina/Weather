package com.antoniok.core.data_source.local.entity.shared

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antoniok.core.model.Condition

@Entity
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

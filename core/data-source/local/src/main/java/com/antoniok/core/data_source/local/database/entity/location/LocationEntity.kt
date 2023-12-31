package com.antoniok.core.data_source.local.database.entity.location

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antoniok.core.data_source.local.database.util.Table
import com.antoniok.core.model.Location

@Entity(tableName = Table.LOCATION)
data class LocationEntity(
    @PrimaryKey(autoGenerate = true)
    val locationId: Int = 0,
    val name: String,
    val region: String,
    val country: String,
    val localtimeEpoch: Int
)

fun LocationEntity.asExternalModule() = Location(
    name = name,
    region = region,
    country = country,
    localtimeEpoch = localtimeEpoch
)

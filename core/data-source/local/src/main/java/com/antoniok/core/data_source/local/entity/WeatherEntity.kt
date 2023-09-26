package com.antoniok.core.data_source.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antoniok.core.data_source.local.entity.current.CurrentEntity
import com.antoniok.core.data_source.local.entity.forecast.AstroEntity
import com.antoniok.core.data_source.local.entity.forecast.DayEntity
import com.antoniok.core.data_source.local.entity.location.LocationEntity
import com.antoniok.core.data_source.local.util.Table

@Entity(tableName = Table.WEATHER)
data class WeatherEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @Embedded val location: LocationEntity,
    @Embedded val current: CurrentEntity,
    @Embedded val day: DayEntity,
    @Embedded val astro: AstroEntity
)

package com.antoniok.core.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.antoniok.core.data_source.local.dao.WeatherDao
import com.antoniok.core.data_source.local.entity.WeatherEntity
import com.antoniok.core.data_source.local.entity.current.CurrentEntity
import com.antoniok.core.data_source.local.entity.forecast.AstroEntity
import com.antoniok.core.data_source.local.entity.forecast.DayEntity
import com.antoniok.core.data_source.local.entity.forecast.HourEntity
import com.antoniok.core.data_source.local.entity.location.LocationEntity
import com.antoniok.core.data_source.local.entity.shared.ConditionEntity

@Database(
    entities = [
        CurrentEntity::class,
        AstroEntity::class,
        DayEntity::class,
        HourEntity::class,
        LocationEntity::class,
        ConditionEntity::class,
        WeatherEntity::class
    ],
    version = 1,
    exportSchema = false
)
internal abstract class WeatherDatabase : RoomDatabase() {
    abstract val weatherDao: WeatherDao
}

package com.antoniok.core.data_source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.antoniok.core.data_source.local.database.dao.WeatherDao
import com.antoniok.core.data_source.local.database.entity.WeatherEntity
import com.antoniok.core.data_source.local.database.entity.current.CurrentEntity
import com.antoniok.core.data_source.local.database.entity.forecast.AstroEntity
import com.antoniok.core.data_source.local.database.entity.forecast.DayEntity
import com.antoniok.core.data_source.local.database.entity.forecast.ForecastDayEntity
import com.antoniok.core.data_source.local.database.entity.forecast.HourEntity
import com.antoniok.core.data_source.local.database.entity.location.LocationEntity
import com.antoniok.core.data_source.local.database.entity.shared.ConditionEntity

@Database(
    entities = [
        CurrentEntity::class,
        AstroEntity::class,
        DayEntity::class,
        ForecastDayEntity::class,
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

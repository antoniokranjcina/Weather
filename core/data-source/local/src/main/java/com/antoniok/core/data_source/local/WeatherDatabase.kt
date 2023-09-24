package com.antoniok.core.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.antoniok.core.data_source.local.dao.ConditionForecastDao
import com.antoniok.core.data_source.local.dao.CurrentWeatherDao
import com.antoniok.core.data_source.local.dao.DailyWeatherForecastDao
import com.antoniok.core.data_source.local.dao.WeatherMetricsDao
import com.antoniok.core.data_source.local.entity.ConditionForecastEntity
import com.antoniok.core.data_source.local.entity.CurrentWeatherEntity
import com.antoniok.core.data_source.local.entity.DailyWeatherForecastEntity
import com.antoniok.core.data_source.local.entity.HourInfoEntity
import com.antoniok.core.data_source.local.entity.WeatherMetricsEntity

@Database(
    entities = [
        ConditionForecastEntity::class,
        CurrentWeatherEntity::class,
        DailyWeatherForecastEntity::class,
        HourInfoEntity::class,
        WeatherMetricsEntity::class
    ],
    version = 0,
    exportSchema = false
)
internal abstract class WeatherDatabase : RoomDatabase() {
    abstract val conditionForecastDao: ConditionForecastDao
    abstract val currentWeatherDao: CurrentWeatherDao
    abstract val dailyWeatherForecastDao: DailyWeatherForecastDao
    abstract val weatherMetricsDao: WeatherMetricsDao
}

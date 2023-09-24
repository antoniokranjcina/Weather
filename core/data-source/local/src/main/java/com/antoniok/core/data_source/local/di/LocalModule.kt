package com.antoniok.core.data_source.local.di

import android.content.Context
import androidx.room.Room
import com.antoniok.core.data_source.local.WeatherDatabase
import com.antoniok.core.data_source.local.WeatherLocalDataSource
import com.antoniok.core.data_source.local.WeatherLocalStorage
import com.antoniok.core.data_source.local.dao.ConditionForecastDao
import com.antoniok.core.data_source.local.dao.CurrentWeatherDao
import com.antoniok.core.data_source.local.dao.DailyWeatherForecastDao
import com.antoniok.core.data_source.local.dao.WeatherMetricsDao
import com.antoniok.core.data_source.local.util.Table
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single { provideDatabase(androidContext()) }

    single { provideConditionForecastDao(get()) }
    single { provideCurrentWeatherDao(get()) }
    single { provideDailyWeatherDao(get()) }
    single { provideWeatherMetricsDao(get()) }

    single { provideWeatherLocalDataSource(get(), get(), get(), get()) }
}

private fun provideDatabase(context: Context): WeatherDatabase = Room
    .databaseBuilder(
        context = context,
        klass = WeatherDatabase::class.java,
        name = Table.DATABASE_NAME
    )
    .build()

private fun provideConditionForecastDao(db: WeatherDatabase) = db.conditionForecastDao
private fun provideCurrentWeatherDao(db: WeatherDatabase) = db.currentWeatherDao
private fun provideDailyWeatherDao(db: WeatherDatabase) = db.dailyWeatherForecastDao
private fun provideWeatherMetricsDao(db: WeatherDatabase) = db.weatherMetricsDao

private fun provideWeatherLocalDataSource(
    conditionForecastDao: ConditionForecastDao,
    currentWeatherDao: CurrentWeatherDao,
    dailyWeatherForecastDao: DailyWeatherForecastDao,
    weatherMetricsDao: WeatherMetricsDao,
): WeatherLocalDataSource = WeatherLocalStorage(
    conditionForecastDao = conditionForecastDao,
    currentWeatherDao = currentWeatherDao,
    dailyWeatherForecastDao = dailyWeatherForecastDao,
    weatherMetricsDao = weatherMetricsDao,
)

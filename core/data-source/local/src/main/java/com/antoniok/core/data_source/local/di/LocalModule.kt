package com.antoniok.core.data_source.local.di

import android.content.Context
import androidx.room.Room
import com.antoniok.core.data_source.local.WeatherLocalDataSource
import com.antoniok.core.data_source.local.WeatherLocalStorage
import com.antoniok.core.data_source.local.database.WeatherDatabase
import com.antoniok.core.data_source.local.database.dao.WeatherDao
import com.antoniok.core.data_source.local.database.util.Table
import com.antoniok.core.data_source.local.preferences.CityIndexDataStore
import com.antoniok.core.data_source.local.preferences.LocalDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single { provideDatabase(androidContext()) }

    single { provideWeatherDao(get()) }

    single { provideWeatherLocalDataSource(get(), get()) }

    single<LocalDataStore> { CityIndexDataStore(androidContext()) }
}

private fun provideDatabase(context: Context): WeatherDatabase = Room
    .databaseBuilder(
        context = context,
        klass = WeatherDatabase::class.java,
        name = Table.DATABASE_NAME
    )
    .build()

private fun provideWeatherDao(db: WeatherDatabase) = db.weatherDao

private fun provideWeatherLocalDataSource(
    weatherDao: WeatherDao,
    localDataStore: LocalDataStore
): WeatherLocalDataSource = WeatherLocalStorage(
    weatherDao = weatherDao,
    localDataStore = localDataStore
)

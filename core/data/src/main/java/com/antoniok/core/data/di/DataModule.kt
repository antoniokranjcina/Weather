package com.antoniok.core.data.di

import com.antoniok.core.data.repository.OfflineFirstWeatherRepository
import com.antoniok.core.data.repository.WeatherRepository
import com.antoniok.core.data_source.local.WeatherLocalDataSource
import com.antoniok.weather.data_source.remote.WeatherNetworkDataSource
import org.koin.dsl.module

val dataModule = module {

    single { provideWeatherRepository(get(), get()) }

}

private fun provideWeatherRepository(
    localDataSource: WeatherLocalDataSource,
    networkDataSource: WeatherNetworkDataSource
): WeatherRepository = OfflineFirstWeatherRepository(
    localDataSource = localDataSource,
    networkDataSource = networkDataSource
)
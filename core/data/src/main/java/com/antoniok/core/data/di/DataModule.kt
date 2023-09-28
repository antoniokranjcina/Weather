package com.antoniok.core.data.di

import com.antoniok.core.data.repository.ModalItemsRepository
import com.antoniok.core.data.repository.SearchCityRepository
import com.antoniok.core.data.repository.WeatherRepository
import com.antoniok.core.data.repository.impl.ModalDataStoreRepository
import com.antoniok.core.data.repository.impl.OfflineFirstWeatherRepository
import com.antoniok.core.data.repository.impl.SearchedCitiesProvider
import com.antoniok.core.data_source.local.WeatherLocalDataSource
import com.antoniok.weather.data_source.remote.WeatherNetworkDataSource
import org.koin.dsl.module

val dataModule = module {

    single { provideWeatherRepository(get(), get()) }
    single { provideModalItemsRepository(get()) }
    single { provideSearchCityRepository(get()) }
}

private fun provideWeatherRepository(
    localDataSource: WeatherLocalDataSource,
    networkDataSource: WeatherNetworkDataSource
): WeatherRepository = OfflineFirstWeatherRepository(
    localDataSource = localDataSource,
    networkDataSource = networkDataSource
)

private fun provideModalItemsRepository(
    localDataSource: WeatherLocalDataSource,
): ModalItemsRepository = ModalDataStoreRepository(
    localDataSource = localDataSource
)

private fun provideSearchCityRepository(
    networkDataSource: WeatherNetworkDataSource
): SearchCityRepository = SearchedCitiesProvider(
    networkDataSource = networkDataSource
)

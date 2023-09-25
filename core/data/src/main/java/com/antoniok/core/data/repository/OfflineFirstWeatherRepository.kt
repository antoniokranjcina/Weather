package com.antoniok.core.data.repository

import com.antoniok.core.data_source.local.WeatherLocalDataSource
import com.antoniok.core.data_source.local.entity.WeatherEntity
import com.antoniok.core.data_source.local.entity.asExternalModule
import com.antoniok.core.model.Weather
import com.antoniok.weather.data_source.remote.WeatherNetworkDataSource
import com.antoniok.weather.data_source.remote.model.asEntity
import com.antoniok.weather.data_source.remote.resource.NetworkResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class OfflineFirstWeatherRepository(
    private val localDataSource: WeatherLocalDataSource,
    private val networkDataSource: WeatherNetworkDataSource
) : WeatherRepository {

    override fun getWeatherByCity(city: String): Flow<Weather?> =
        localDataSource.getWeatherByCity(city)
            .map { weatherEntity: WeatherEntity? ->
                weatherEntity?.asExternalModule()
            }

    override val weathers: Flow<List<Weather>>
        get() = localDataSource.weathers.map { it.map(WeatherEntity::asExternalModule) }

    override suspend fun sync(city: String, days: Int): Boolean {
        val weather = networkDataSource.getWeather(
            city = city,
            days = days
        )
        return when (weather) {
            is NetworkResource.Success -> {
                localDataSource.insertWeather(weather.data.asEntity())
                true
            }

            is NetworkResource.Error -> {
                weather.exception.printStackTrace()
                false
            }
        }
    }

}

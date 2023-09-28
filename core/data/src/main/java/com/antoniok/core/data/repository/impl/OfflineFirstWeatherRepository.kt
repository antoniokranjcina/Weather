package com.antoniok.core.data.repository.impl

import com.antoniok.core.data.repository.WeatherRepository
import com.antoniok.core.data.util.getCityFromNetworkAndSaveIt
import com.antoniok.core.data_source.local.WeatherLocalDataSource
import com.antoniok.core.data_source.local.database.entity.WeatherWithDaysAndHours
import com.antoniok.core.data_source.local.database.entity.asExternalModule
import com.antoniok.core.model.Weather
import com.antoniok.weather.data_source.remote.WeatherNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

internal class OfflineFirstWeatherRepository(
    private val localDataSource: WeatherLocalDataSource,
    private val networkDataSource: WeatherNetworkDataSource
) : WeatherRepository {

    override fun getWeatherByCity(city: String): Flow<Weather?> =
        localDataSource.getWeatherByCity(city)
            .map { weather ->
                weather?.asExternalModule()
            }

    override val weathers: Flow<List<Weather>>
        get() = localDataSource.weathers.map { it.map(WeatherWithDaysAndHours::asExternalModule) }

    override suspend fun sync(city: String, days: Int): Boolean {
        val cities = localDataSource.cities.first()
        return if (cities.isNotEmpty()) {
            cities.forEach {
                getCityFromNetworkAndSaveIt(
                    city = it,
                    days = days,
                    networkDataSource = networkDataSource,
                    localDataSource = localDataSource
                )
            }
            true
        } else {
            getCityFromNetworkAndSaveIt(
                city = city,
                days = days,
                networkDataSource = networkDataSource,
                localDataSource = localDataSource
            )
        }
    }
}

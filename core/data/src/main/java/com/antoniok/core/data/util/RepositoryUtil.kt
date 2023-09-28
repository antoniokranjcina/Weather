package com.antoniok.core.data.util

import com.antoniok.core.data_source.local.WeatherLocalDataSource
import com.antoniok.weather.data_source.remote.WeatherNetworkDataSource
import com.antoniok.weather.data_source.remote.model.asEntity
import com.antoniok.weather.data_source.remote.model.forecast.asEntity
import com.antoniok.weather.data_source.remote.resource.NetworkResource


/**
 * Fetches weather data for a specified city from the network and saves it to the local database.
 *
 * @param city The name of the city for which weather data is to be retrieved.
 * @param days The number of days for which the weather forecast is requested.
 * @param networkDataSource The data source for fetching weather information from the network.
 * @param localDataSource The data source for saving weather information to the local database.
 * @return `true` if the operation was successful and data was saved, `false` otherwise.
 */
internal suspend fun getCityFromNetworkAndSaveIt(
    city: String,
    days: Int,
    networkDataSource: WeatherNetworkDataSource,
    localDataSource: WeatherLocalDataSource
): Boolean {
    val weather = networkDataSource.getWeather(
        city = city,
        days = days
    )
    return when (weather) {
        is NetworkResource.Success -> {
            localDataSource.insertWeather(weather.data.asEntity())
            localDataSource.insertHours(
                weather.data.forecast.forecastDay.first().hour.map {
                    it.asEntity(weather.data.location.name)
                }
            )
            localDataSource.insertForecastDays(
                weather.data.forecast.forecastDay.map {
                    it.asEntity(weather.data.location.name)
                }
            )
            true
        }

        is NetworkResource.Error -> {
            weather.exception.printStackTrace()
            false
        }
    }
}

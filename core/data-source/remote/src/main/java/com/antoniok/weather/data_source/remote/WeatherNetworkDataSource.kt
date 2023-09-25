package com.antoniok.weather.data_source.remote

import com.antoniok.weather.data_source.remote.model.WeatherDto
import com.antoniok.weather.data_source.remote.resource.NetworkResource

/**
 * A data source interface for retrieving weather information from a network service.
 */
interface WeatherNetworkDataSource {

    /**
     * Retrieves weather information for a specified city.
     *
     * @param city The name of the city for which weather information is requested.
     * @param days The number of days for which weather forecasts are needed.
     * @return A [NetworkResource] containing the weather data in the form of a [WeatherDto].
     */
    suspend fun getWeather(city: String, days: Int): NetworkResource<WeatherDto>
}

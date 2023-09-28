package com.antoniok.weather.data_source.remote

import com.antoniok.weather.data_source.remote.model.WeatherDto
import com.antoniok.weather.data_source.remote.model.location.SearchedLocationDto
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

    /**
     * Retrieves a list of locations based on the provided [location] name from a remote data source.
     *
     * @param location The location of the city to searched for.
     * @return A [NetworkResource] representing the result of the operation.
     *         - If the operation is successful, it contains a list of [SearchedLocationDto].
     *         - If an error occurs, it contains an exception.
     */
    suspend fun getLocations(location: String): NetworkResource<List<SearchedLocationDto>>
}

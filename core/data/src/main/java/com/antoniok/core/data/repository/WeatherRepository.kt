package com.antoniok.core.data.repository

import com.antoniok.core.data.Sync
import com.antoniok.core.model.Weather
import kotlinx.coroutines.flow.Flow

/**
 * A repository interface for retrieving weather data.
 */
interface WeatherRepository : Sync {

    /**
     * Retrieves weather data for a specific city.
     *
     * @param city The name of the city for which weather data is requested.
     * @return A [Flow] emitting the [Weather] object for the specified city, or null if not found.
     */
    fun getWeatherByCity(city: String): Flow<Weather?>

    /**
     * Retrieves a list of weather data for multiple cities.
     *
     * @return A [Flow] emitting a list of [Weather] objects representing weather data for multiple
     * cities.
     */
    val weathers: Flow<List<Weather>>

}

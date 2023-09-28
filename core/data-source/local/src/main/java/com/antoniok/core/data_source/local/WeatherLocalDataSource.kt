package com.antoniok.core.data_source.local

import com.antoniok.core.data_source.local.database.entity.WeatherEntity
import com.antoniok.core.data_source.local.database.entity.WeatherWithDaysAndHours
import com.antoniok.core.data_source.local.database.entity.forecast.ForecastDayEntity
import com.antoniok.core.data_source.local.database.entity.forecast.HourEntity
import kotlinx.coroutines.flow.Flow

/**
 * A data source interface responsible for managing weather data in a local storage.
 */
interface WeatherLocalDataSource {

    /**
     * Inserts a weather entity into the local storage.
     *
     * @param weather The [WeatherEntity] to be inserted.
     */
    suspend fun insertWeather(weather: WeatherEntity)

    suspend fun insertHours(hours: List<HourEntity>)

    suspend fun insertForecastDays(forecastDay: List<ForecastDayEntity>)

    /**
     * Retrieves weather data for a specific city from the local storage.
     *
     * @param city The name of the city for which weather data is requested.
     * @return A [Flow] emitting the [WeatherEntity] for the specified city, or null if not found.
     */
    fun getWeatherByCity(city: String): Flow<WeatherWithDaysAndHours?>

    /**
     * Retrieves a list of all weather entities stored in the local storage.
     *
     * @return A [Flow] emitting a list of [WeatherEntity] objects.
     */
    val weathers: Flow<List<WeatherWithDaysAndHours>>

    /**
     * Retrieves a list of all city entities stored in the local storage.
     *
     * @return A [Flow] emitting a list of [String] objects.
     */
    val cities: Flow<List<String>>

    /**
     * Deletes all weather entities from the local storage.
     */
    suspend fun deleteAllWeathers()

    /**
     * Deletes a specific weather entity from the local storage.
     *
     * @param weather The [WeatherEntity] to be deleted.
     */
    suspend fun deleteWeather(weather: WeatherEntity)

    suspend fun saveIndex(index: Int)

    val index: Flow<Int>

}

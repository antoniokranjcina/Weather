package com.antoniok.core.data.repository

import com.antoniok.core.model.ConditionForecast
import com.antoniok.core.model.CurrentWeather
import com.antoniok.core.model.DailyWeatherForecast
import com.antoniok.core.model.WeatherMetrics
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining methods to retrieve weather-related data using Flow-based asynchronous
 * programming. Implementations of this interface should provide functionality to fetch various
 * weather forecasts and metrics.
 */
interface WeatherRepository {

    /**
     * Fetches the current condition forecast asynchronously as a Flow of [ConditionForecast].
     *
     * @return A Flow emitting the current condition forecast data.
     */
    fun getConditionForecast(): Flow<ConditionForecast>

    /**
     * Fetches the current weather data asynchronously as a Flow of [CurrentWeather].
     *
     * @return A Flow emitting the current weather data.
     */
    fun getCurrentWeather(): Flow<CurrentWeather>

    /**
     * Fetches the daily weather forecast asynchronously as a Flow of [DailyWeatherForecast].
     *
     * @return A Flow emitting the daily weather forecast data.
     */
    fun getDailyWeatherForecast(): Flow<DailyWeatherForecast>

    /**
     * Fetches weather metrics asynchronously as a Flow of [WeatherMetrics].
     *
     * @return A Flow emitting weather metrics data.
     */
    fun getWeatherMetrics(): Flow<WeatherMetrics>

}

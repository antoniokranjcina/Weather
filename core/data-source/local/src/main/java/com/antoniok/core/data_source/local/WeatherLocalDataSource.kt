package com.antoniok.core.data_source.local

import com.antoniok.core.data_source.local.entity.ConditionForecastEntity
import com.antoniok.core.data_source.local.entity.ConditionForecastWithHours
import com.antoniok.core.data_source.local.entity.CurrentWeatherEntity
import com.antoniok.core.data_source.local.entity.DailyWeatherForecastEntity
import com.antoniok.core.data_source.local.entity.HourInfoEntity
import com.antoniok.core.data_source.local.entity.WeatherMetricsEntity
import kotlinx.coroutines.flow.Flow

/**
 * A data source interface for managing local weather data.
 */
interface WeatherLocalDataSource {

    /**
     * Inserts a [ConditionForecastWithHours] into the local database.
     *
     * @param condition The [ConditionForecastWithHours] to insert.
     */
    suspend fun insertConditionForecastWithHours(condition: ConditionForecastWithHours)

    /**
     * Inserts a [CurrentWeatherEntity] into the local database.
     *
     * @param weather The [CurrentWeatherEntity] to insert.
     */
    suspend fun insertCurrentWeather(weather: CurrentWeatherEntity)

    /**
     * Inserts a [DailyWeatherForecastEntity] into the local database.
     *
     * @param dailyWeather The [DailyWeatherForecastEntity] to insert.
     */
    suspend fun insertDailyWeatherForecast(dailyWeather: DailyWeatherForecastEntity)

    /**
     * Inserts a [WeatherMetricsEntity] into the local database.
     *
     * @param weatherMetrics The [WeatherMetricsEntity] to insert.
     */
    suspend fun insertWeatherMetrics(weatherMetrics: WeatherMetricsEntity)

    /**
     * Retrieves a [Flow] of [ConditionForecastWithHours] from the local database.
     */
    val conditionForecastWithHours: Flow<ConditionForecastWithHours>

    /**
     * Retrieves a [Flow] of [CurrentWeatherEntity] from the local database.
     */
    val currentWeather: Flow<CurrentWeatherEntity>

    /**
     * Retrieves a [Flow] of [DailyWeatherForecastEntity] from the local database.
     */
    val dailyWeatherForecast: Flow<DailyWeatherForecastEntity>

    /**
     * Retrieves a [Flow] of [WeatherMetricsEntity] from the local database.
     */
    val weatherMetrics: Flow<WeatherMetricsEntity>

    /**
     * Updates an existing [ConditionForecastWithHours] in the local database.
     *
     * @param conditionForecastWithHours The updated [ConditionForecastWithHours].
     */
    suspend fun updateConditionForecast(conditionForecastWithHours: ConditionForecastWithHours)

    /**
     * Updates an existing [CurrentWeatherEntity] in the local database.
     *
     * @param currentWeather The updated [CurrentWeatherEntity].
     */
    suspend fun updateCurrentWeather(currentWeather: CurrentWeatherEntity)

    /**
     * Updates an existing [DailyWeatherForecastEntity] in the local database.
     *
     * @param dailyWeather The updated [DailyWeatherForecastEntity].
     */
    suspend fun updateDailyWeatherForecast(dailyWeather: DailyWeatherForecastEntity)

    /**
     * Updates an existing [WeatherMetricsEntity] in the local database.
     *
     * @param weatherMetrics The updated [WeatherMetricsEntity].
     */
    suspend fun updateWeatherMetrics(weatherMetrics: WeatherMetricsEntity)

    /**
     * Deletes a [ConditionForecastEntity] from the local database.
     *
     * @param conditionForecast The [ConditionForecastEntity] to delete.
     */
    suspend fun deleteConditionForecast(conditionForecast: ConditionForecastEntity)

    /**
     * Deletes a [HourInfoEntity] from the local database.
     *
     * @param hoursInfo The [HourInfoEntity] to delete.
     */
    suspend fun deleteHour(hoursInfo: HourInfoEntity)

    /**
     * Deletes the current weather data from the local database.
     *
     * @param currentWeather The [CurrentWeatherEntity] to delete.
     */
    suspend fun deleteCurrentWeather(currentWeather: CurrentWeatherEntity)

    /**
     * Deletes the daily weather forecast data from the local database.
     *
     * @param dailyWeather The [DailyWeatherForecastEntity] to delete.
     */
    suspend fun deleteDailyWeatherForecast(dailyWeather: DailyWeatherForecastEntity)

    /**
     * Deletes the weather metrics data from the local database.
     *
     * @param weatherMetrics The [WeatherMetricsEntity] to delete.
     */
    suspend fun deleteWeatherMetrics(weatherMetrics: WeatherMetricsEntity)
}

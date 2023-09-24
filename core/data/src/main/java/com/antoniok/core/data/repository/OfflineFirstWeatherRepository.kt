package com.antoniok.core.data.repository

import com.antoniok.core.data_source.local.WeatherLocalDataSource
import com.antoniok.core.data_source.local.entity.ConditionForecastWithHours
import com.antoniok.core.data_source.local.entity.CurrentWeatherEntity
import com.antoniok.core.data_source.local.entity.DailyWeatherForecastEntity
import com.antoniok.core.data_source.local.entity.WeatherMetricsEntity
import com.antoniok.core.data_source.local.mapper.asExternalModel
import com.antoniok.core.model.ConditionForecast
import com.antoniok.core.model.CurrentWeather
import com.antoniok.core.model.DailyWeatherForecast
import com.antoniok.core.model.WeatherMetrics
import com.antoniok.weather.data_source.remote.WeatherNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class OfflineFirstWeatherRepository(
    private val localDataSource: WeatherLocalDataSource,
    private val networkDataSource: WeatherNetworkDataSource
) : WeatherRepository {

    override fun getConditionForecast(): Flow<ConditionForecast> =
        localDataSource.conditionForecastWithHours.map(ConditionForecastWithHours::asExternalModel)

    override fun getCurrentWeather(): Flow<CurrentWeather> =
        localDataSource.currentWeather.map(CurrentWeatherEntity::asExternalModel)

    override fun getDailyWeatherForecast(): Flow<DailyWeatherForecast> =
        localDataSource.dailyWeatherForecast.map(DailyWeatherForecastEntity::asExternalModel)

    override fun getWeatherMetrics(): Flow<WeatherMetrics> =
        localDataSource.weatherMetrics.map(WeatherMetricsEntity::asExternalModel)

}

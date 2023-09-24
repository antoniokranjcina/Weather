package com.antoniok.core.data.repository

import com.antoniok.core.data_source.local.WeatherLocalDataSource
import com.antoniok.core.model.ConditionForecast
import com.antoniok.core.model.CurrentWeather
import com.antoniok.core.model.DailyWeatherForecast
import com.antoniok.core.model.WeatherMetrics
import com.antoniok.weather.data_source.remote.WeatherNetworkDataSource
import kotlinx.coroutines.flow.Flow

// TODO check for model gradle -> !!

// TODO preparation over - commit this
//  after that start writing logic

internal class OfflineFirstWeatherRepository(
    private val localDataSource: WeatherLocalDataSource,
    private val networkDataSource: WeatherNetworkDataSource
) : WeatherRepository {
    override fun getConditionForecast(): Flow<ConditionForecast> {
        TODO("Not yet implemented")
    }

    override fun getCurrentWeather(): Flow<CurrentWeather> {
        TODO("Not yet implemented")
    }

    override fun getDailyWeatherForecast(): Flow<DailyWeatherForecast> {
        TODO("Not yet implemented")
    }

    override fun getWeatherMetrics(): Flow<WeatherMetrics> {
        TODO("Not yet implemented")
    }

}

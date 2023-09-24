package com.antoniok.core.data.repository

import com.antoniok.core.model.ConditionForecast
import com.antoniok.core.model.CurrentWeather
import com.antoniok.core.model.DailyWeatherForecast
import com.antoniok.core.model.WeatherMetrics
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getConditionForecast(): Flow<ConditionForecast>

    fun getCurrentWeather(): Flow<CurrentWeather>

    fun getDailyWeatherForecast(): Flow<DailyWeatherForecast>

    fun getWeatherMetrics(): Flow<WeatherMetrics>

}

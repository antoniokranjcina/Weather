package com.antoniok.core.data_source.local

import com.antoniok.core.data_source.local.dao.WeatherDao
import com.antoniok.core.data_source.local.entity.WeatherEntity
import com.antoniok.core.data_source.local.entity.WeatherWithDaysAndHours
import com.antoniok.core.data_source.local.entity.forecast.ForecastDayEntity
import com.antoniok.core.data_source.local.entity.forecast.HourEntity
import kotlinx.coroutines.flow.Flow

internal class WeatherLocalStorage(private val weatherDao: WeatherDao) : WeatherLocalDataSource {

    override suspend fun insertWeather(weather: WeatherEntity) {
        weatherDao.insertWeather(weather)
    }

    override suspend fun insertHours(hours: List<HourEntity>) {
        weatherDao.insertHours(hours)
    }

    override suspend fun insertForecastDays(forecastDay: List<ForecastDayEntity>) {
        weatherDao.insertForecastDays(forecastDay)
    }

    override fun getWeatherByCity(city: String): Flow<WeatherWithDaysAndHours?> =
        weatherDao.getWeatherByCity(city)

    override val weathers: Flow<List<WeatherWithDaysAndHours>>
        get() = weatherDao.getAllWeathers()

    override suspend fun deleteAllWeathers() {
        weatherDao.deleteAllWeathers()
    }

    override suspend fun deleteWeather(weather: WeatherEntity) {
        weatherDao.deleteWeather(weather)
    }

}

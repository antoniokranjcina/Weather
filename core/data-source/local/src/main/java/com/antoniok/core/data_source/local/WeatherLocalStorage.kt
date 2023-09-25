package com.antoniok.core.data_source.local

import com.antoniok.core.data_source.local.dao.WeatherDao
import com.antoniok.core.data_source.local.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow

internal class WeatherLocalStorage(private val weatherDao: WeatherDao) : WeatherLocalDataSource {

    override suspend fun insertWeather(weather: WeatherEntity) {
        weatherDao.insertWeather(weather)
    }

    override fun getWeatherByCity(city: String): Flow<WeatherEntity?> =
        weatherDao.getWeatherByCity(city)

    override val weathers: Flow<List<WeatherEntity>>
        get() = weatherDao.getAllWeathers()

    override suspend fun deleteAllWeathers() {
        weatherDao.deleteAllWeathers()
    }

    override suspend fun deleteWeather(weather: WeatherEntity) {
        weatherDao.deleteWeather(weather)
    }

}

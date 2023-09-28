package com.antoniok.core.data_source.local

import com.antoniok.core.data_source.local.database.dao.WeatherDao
import com.antoniok.core.data_source.local.database.entity.WeatherEntity
import com.antoniok.core.data_source.local.database.entity.WeatherWithDaysAndHours
import com.antoniok.core.data_source.local.database.entity.forecast.ForecastDayEntity
import com.antoniok.core.data_source.local.database.entity.forecast.HourEntity
import com.antoniok.core.data_source.local.preferences.LocalDataStore
import kotlinx.coroutines.flow.Flow

internal class WeatherLocalStorage(
    private val weatherDao: WeatherDao,
    private val localDataStore: LocalDataStore
) : WeatherLocalDataSource {

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

    override val cities: Flow<List<String>>
        get() = weatherDao.getCities()

    override suspend fun deleteAllWeathers() {
        weatherDao.deleteAllWeathers()
    }

    override suspend fun deleteWeather(weather: WeatherEntity) {
        weatherDao.deleteWeather(weather)
    }

    override suspend fun saveIndex(index: Int) {
        localDataStore.saveIndex(index)
    }

    override val index: Flow<Int>
        get() = localDataStore.index

}

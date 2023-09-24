package com.antoniok.core.data_source.local

import com.antoniok.core.data_source.local.dao.ConditionForecastDao
import com.antoniok.core.data_source.local.dao.CurrentWeatherDao
import com.antoniok.core.data_source.local.dao.DailyWeatherForecastDao
import com.antoniok.core.data_source.local.dao.WeatherMetricsDao
import com.antoniok.core.data_source.local.entity.ConditionForecastEntity
import com.antoniok.core.data_source.local.entity.ConditionForecastWithHours
import com.antoniok.core.data_source.local.entity.CurrentWeatherEntity
import com.antoniok.core.data_source.local.entity.DailyWeatherForecastEntity
import com.antoniok.core.data_source.local.entity.HourInfoEntity
import com.antoniok.core.data_source.local.entity.WeatherMetricsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

internal class WeatherLocalStorage(
    private val conditionForecastDao: ConditionForecastDao,
    private val currentWeatherDao: CurrentWeatherDao,
    private val dailyWeatherForecastDao: DailyWeatherForecastDao,
    private val weatherMetricsDao: WeatherMetricsDao
) : WeatherLocalDataSource {
    override suspend fun insertConditionForecastWithHours(condition: ConditionForecastWithHours) {
        conditionForecastDao.apply {
            insertConditionForecast(condition.conditionForecast)
            insertHourInfo(condition.hoursInfo)
        }
    }

    override suspend fun insertCurrentWeather(weather: CurrentWeatherEntity) {
        currentWeatherDao.insertCurrentWeather(weather)
    }

    override suspend fun insertDailyWeatherForecast(dailyWeather: DailyWeatherForecastEntity) {
        dailyWeatherForecastDao.insertDailyWeatherForecast(dailyWeather)
    }

    override suspend fun insertWeatherMetrics(weatherMetrics: WeatherMetricsEntity) {
        weatherMetricsDao.insertWeatherMetrics(weatherMetrics)
    }

    override val conditionForecastWithHours: Flow<ConditionForecastWithHours>
        get() = combine(
            conditionForecastDao.getConditionForecasts(),
            conditionForecastDao.getHoursInfo()
        ) { conditionForecast, hoursInfo ->
            ConditionForecastWithHours(conditionForecast, hoursInfo)
        }
    override val currentWeather: Flow<CurrentWeatherEntity>
        get() = currentWeatherDao.getCurrentWeather()
    override val dailyWeatherForecast: Flow<DailyWeatherForecastEntity>
        get() = dailyWeatherForecastDao.getDailyWeatherForecast()
    override val weatherMetrics: Flow<WeatherMetricsEntity>
        get() = weatherMetricsDao.getWeatherMetrics()

    override suspend fun updateConditionForecast(
        conditionForecastWithHours: ConditionForecastWithHours
    ) {
        conditionForecastDao.apply {
            updateConditionForecast(conditionForecastWithHours.conditionForecast)
            updateHours(conditionForecastWithHours.hoursInfo)
        }
    }

    override suspend fun updateCurrentWeather(currentWeather: CurrentWeatherEntity) {
        currentWeatherDao.updateCurrentWeather(currentWeather)
    }

    override suspend fun updateDailyWeatherForecast(dailyWeather: DailyWeatherForecastEntity) {
        dailyWeatherForecastDao.updateDailyWeatherForecast(dailyWeather)
    }

    override suspend fun updateWeatherMetrics(weatherMetrics: WeatherMetricsEntity) {
        weatherMetricsDao.updateWeatherMetrics(weatherMetrics)
    }

    override suspend fun deleteConditionForecast(conditionForecast: ConditionForecastEntity) {
        conditionForecastDao.deleteConditionForecast(conditionForecast)
    }

    override suspend fun deleteHour(hoursInfo: HourInfoEntity) {
        conditionForecastDao.deleteHour(hoursInfo)
    }

    override suspend fun deleteCurrentWeather(currentWeather: CurrentWeatherEntity) {
        currentWeatherDao.deleteCurrentWeather(currentWeather)
    }

    override suspend fun deleteDailyWeatherForecast(dailyWeather: DailyWeatherForecastEntity) {
        dailyWeatherForecastDao.deleteDailyWeatherForecast(dailyWeather)
    }

    override suspend fun deleteWeatherMetrics(weatherMetrics: WeatherMetricsEntity) {
        weatherMetricsDao.deleteWeatherMetrics(weatherMetrics)
    }

}

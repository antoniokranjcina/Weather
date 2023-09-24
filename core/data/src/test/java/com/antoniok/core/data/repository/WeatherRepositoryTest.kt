package com.antoniok.core.data.repository

import com.antoniok.core.data_source.local.WeatherLocalDataSource
import com.antoniok.core.data_source.local.entity.ConditionForecastEntity
import com.antoniok.core.data_source.local.entity.ConditionForecastWithHours
import com.antoniok.core.data_source.local.entity.CurrentWeatherEntity
import com.antoniok.core.data_source.local.entity.DailyWeatherForecastEntity
import com.antoniok.core.data_source.local.entity.WeatherMetricsEntity
import com.antoniok.core.data_source.local.mapper.asExternalModel
import com.antoniok.weather.data_source.remote.WeatherNetworkDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`

class OfflineFirstWeatherRepositoryTest {

    private lateinit var localDataSource: WeatherLocalDataSource
    private lateinit var networkDataSource: WeatherNetworkDataSource
    private lateinit var repository: OfflineFirstWeatherRepository

    @Before
    fun setUp() {
        localDataSource = mock(WeatherLocalDataSource::class.java)
        networkDataSource = mock(WeatherNetworkDataSource::class.java)
        repository = OfflineFirstWeatherRepository(localDataSource, networkDataSource)
    }

    @Test
    fun `GIVEN local data source has condition forecast WHEN getConditionForecast is called THEN return the data`() =
        runBlocking {
            `when`(localDataSource.conditionForecastWithHours)
                .thenReturn(flow { emit(conditionForecast) })

            val result = repository.getConditionForecast().first()

            assertEquals(conditionForecast.asExternalModel(), result)
            verify(localDataSource).conditionForecastWithHours
            verifyNoMoreInteractions(localDataSource)
        }

    @Test
    fun `GIVEN local data source has current weather WHEN getCurrentWeather is called THEN return the data`() =
        runBlocking {
            `when`(localDataSource.currentWeather).thenReturn(flow { emit(currentWeatherEntity) })

            val result = repository.getCurrentWeather().first()

            assertEquals(currentWeatherEntity.asExternalModel(), result)
            verify(localDataSource).currentWeather
            verifyNoMoreInteractions(localDataSource)
        }

    @Test
    fun `GIVEN local data source has daily weather forecast WHEN getDailyWeatherForecast is called THEN return the data`() =
        runBlocking {
            `when`(localDataSource.dailyWeatherForecast)
                .thenReturn(flow { emit(dailyWeatherForecastEntity) })

            val result = repository.getDailyWeatherForecast().first()

            assertEquals(dailyWeatherForecastEntity.asExternalModel(), result)
            verify(localDataSource).dailyWeatherForecast
            verifyNoMoreInteractions(localDataSource)
        }

    @Test
    fun `GIVEN local data source has weather metrics WHEN getWeatherMetrics is called THEN return the data`() =
        runBlocking {
            `when`(localDataSource.weatherMetrics).thenReturn(flow { emit(weatherMetricsEntity) })

            val result = repository.getWeatherMetrics().first()

            assertEquals(weatherMetricsEntity.asExternalModel(), result)
            verify(localDataSource).weatherMetrics
            verifyNoMoreInteractions(localDataSource)
        }

    companion object {
        private val conditionForecast = ConditionForecastWithHours(
            conditionForecast = ConditionForecastEntity(
                condition = "",
                minTemperature = 20
            ),
            hoursInfo = emptyList()
        )

        private val currentWeatherEntity = CurrentWeatherEntity(
            realTemperature = 15,
            description = "Sunny day",
            descriptionImage = "",
            feelsLikeTemperature = 20,
        )

        private val dailyWeatherForecastEntity = DailyWeatherForecastEntity(
            day = 1,
            chanceOfRain = 20,
            minConditionImage = "",
            maxConditionImage = "",
            minTemp = 10,
            maxTemp = 20,
        )

        private val weatherMetricsEntity = WeatherMetricsEntity(
            uvIndex = "",
            humidity = 20.5,
            wind = 10.2,
            sunrise = "",
            sunset = "",
        )
    }

}

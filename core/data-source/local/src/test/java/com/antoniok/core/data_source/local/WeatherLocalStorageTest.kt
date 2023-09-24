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
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class WeatherLocalStorageTest {

    private lateinit var weatherLocalStorage: WeatherLocalDataSource

    @Mock
    private lateinit var conditionForecastDao: ConditionForecastDao

    @Mock
    private lateinit var currentWeatherDao: CurrentWeatherDao

    @Mock
    private lateinit var dailyWeatherForecastDao: DailyWeatherForecastDao

    @Mock
    private lateinit var weatherMetricsDao: WeatherMetricsDao

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        weatherLocalStorage = WeatherLocalStorage(
            conditionForecastDao,
            currentWeatherDao,
            dailyWeatherForecastDao,
            weatherMetricsDao
        )
    }

    @Test
    fun `Given valid ConditionForecastWithHours data, When insertConditionForecastWithHours is called, Then data is inserted correctly`() =
        runBlocking {
            weatherLocalStorage.insertConditionForecastWithHours(conditionForecastWithHours)
            verify(conditionForecastDao).insertConditionForecast(conditionForecastWithHours.conditionForecast)
            verify(conditionForecastDao).insertHourInfo(conditionForecastWithHours.hoursInfo)
        }

    @Test
    fun `Given valid condition forecast and hours info data in the database, When conditionForecastWithHours is accessed, Then the expected data is returned`() =
        runBlocking {
            val expectedData = ConditionForecastWithHours(
                conditionForecastEntity,
                listOf(hourInfoEntity)
            )

            `when`(conditionForecastDao.getConditionForecasts())
                .thenReturn(flowOf(conditionForecastEntity))
            `when`(conditionForecastDao.getHoursInfo())
                .thenReturn(flowOf(listOf(hourInfoEntity)))

            val flow = weatherLocalStorage.conditionForecastWithHours
            val result = flow.single()

            assert(result == expectedData)
        }

    @Test
    fun `GIVEN valid ConditionForecastWithHours data, WHEN insertConditionForecastWithHours is called, THEN data is inserted correctly`() =
        runBlocking {
            weatherLocalStorage.insertConditionForecastWithHours(conditionForecastWithHours)
            verify(conditionForecastDao).insertConditionForecast(conditionForecastWithHours.conditionForecast)
            verify(conditionForecastDao).insertHourInfo(conditionForecastWithHours.hoursInfo)
        }

    @Test
    fun `GIVEN valid condition forecast and hours info data in the database, WHEN conditionForecastWithHours is accessed, THEN the expected data is returned`() =
        runBlocking {
            val expectedData = ConditionForecastWithHours(
                conditionForecastEntity,
                listOf(hourInfoEntity)
            )

            `when`(conditionForecastDao.getConditionForecasts())
                .thenReturn(flowOf(conditionForecastEntity))
            `when`(conditionForecastDao.getHoursInfo())
                .thenReturn(flowOf(listOf(hourInfoEntity)))

            val flow = weatherLocalStorage.conditionForecastWithHours
            val result = flow.single()

            assert(result == expectedData)
        }

    @Test
    fun `GIVEN valid CurrentWeatherEntity data, WHEN insertCurrentWeather is called, THEN data is inserted correctly`() =
        runBlocking {
            weatherLocalStorage.insertCurrentWeather(currentWeatherEntity)
            verify(currentWeatherDao).insertCurrentWeather(currentWeatherEntity)
        }

    @Test
    fun `GIVEN valid current weather data in the database, WHEN currentWeather is accessed, THEN the expected data is returned`() =
        runBlocking {
            `when`(currentWeatherDao.getCurrentWeather()).thenReturn(flowOf(currentWeatherEntity))

            val flow = weatherLocalStorage.currentWeather
            val result = flow.single()

            assert(result == currentWeatherEntity)
        }

    @Test
    fun `GIVEN valid DailyWeatherForecastEntity data, WHEN insertDailyWeatherForecast is called, THEN data is inserted correctly`() =
        runBlocking {
            weatherLocalStorage.insertDailyWeatherForecast(dailyWeatherForecastEntity)
            verify(dailyWeatherForecastDao).insertDailyWeatherForecast(dailyWeatherForecastEntity)
        }

    @Test
    fun `GIVEN valid daily weather forecast data in the database, WHEN dailyWeatherForecast is accessed, THEN the expected data is returned`() =
        runBlocking {
            `when`(dailyWeatherForecastDao.getDailyWeatherForecast()).thenReturn(
                flowOf(dailyWeatherForecastEntity)
            )

            val flow = weatherLocalStorage.dailyWeatherForecast
            val result = flow.single()

            assert(result == dailyWeatherForecastEntity)
        }

    @Test
    fun `GIVEN valid WeatherMetricsEntity data, WHEN insertWeatherMetrics is called, THEN data is inserted correctly`() =
        runBlocking {
            weatherLocalStorage.insertWeatherMetrics(weatherMetricsEntity)
            verify(weatherMetricsDao).insertWeatherMetrics(weatherMetricsEntity)
        }

    @Test
    fun `GIVEN valid weather metrics data in the database, WHEN weatherMetrics is accessed, THEN the expected data is returned`() =
        runBlocking {
            `when`(weatherMetricsDao.getWeatherMetrics()).thenReturn(flowOf(weatherMetricsEntity))
            val flow = weatherLocalStorage.weatherMetrics
            val result = flow.single()

            assert(result == weatherMetricsEntity)
        }

    @Test
    fun `GIVEN valid ConditionForecastWithHours data, WHEN updateConditionForecast is called, THEN data is updated correctly`() =
        runBlocking {
            weatherLocalStorage.updateConditionForecast(conditionForecastWithHours)
            verify(conditionForecastDao).updateConditionForecast(conditionForecastWithHours.conditionForecast)
            verify(conditionForecastDao).updateHours(conditionForecastWithHours.hoursInfo)
        }

    @Test
    fun `GIVEN valid CurrentWeatherEntity data, WHEN updateCurrentWeather is called, THEN data is updated correctly`() =
        runBlocking {
            weatherLocalStorage.updateCurrentWeather(currentWeatherEntity)
            verify(currentWeatherDao).updateCurrentWeather(currentWeatherEntity)
        }

    @Test
    fun `GIVEN valid DailyWeatherForecastEntity data, WHEN updateDailyWeatherForecast is called, THEN data is updated correctly`() =
        runBlocking {
            weatherLocalStorage.updateDailyWeatherForecast(dailyWeatherForecastEntity)
            verify(dailyWeatherForecastDao).updateDailyWeatherForecast(dailyWeatherForecastEntity)
        }

    @Test
    fun `GIVEN valid WeatherMetricsEntity data, WHEN updateWeatherMetrics is called, THEN data is updated correctly`() =
        runBlocking {
            weatherLocalStorage.updateWeatherMetrics(weatherMetricsEntity)
            verify(weatherMetricsDao).updateWeatherMetrics(weatherMetricsEntity)
        }

    @Test
    fun `GIVEN valid ConditionForecastEntity data, WHEN deleteConditionForecast is called, THEN data is deleted correctly`() =
        runBlocking {
            weatherLocalStorage.deleteConditionForecast(conditionForecastEntity)
            verify(conditionForecastDao).deleteConditionForecast(conditionForecastEntity)
        }

    @Test
    fun `GIVEN valid HourInfoEntity data, WHEN deleteHour is called, THEN data is deleted correctly`() =
        runBlocking {
            weatherLocalStorage.deleteHour(hourInfoEntity)
            verify(conditionForecastDao).deleteHour(hourInfoEntity)
        }

    @Test
    fun `GIVEN valid CurrentWeatherEntity data, WHEN deleteCurrentWeather is called, THEN data is deleted correctly`() =
        runBlocking {
            weatherLocalStorage.deleteCurrentWeather(currentWeatherEntity)
            verify(currentWeatherDao).deleteCurrentWeather(currentWeatherEntity)
        }

    @Test
    fun `GIVEN valid DailyWeatherForecastEntity data, WHEN deleteDailyWeatherForecast is called, THEN data is deleted correctly`() =
        runBlocking {
            weatherLocalStorage.deleteDailyWeatherForecast(dailyWeatherForecastEntity)
            verify(dailyWeatherForecastDao).deleteDailyWeatherForecast(dailyWeatherForecastEntity)
        }

    @Test
    fun `GIVEN valid WeatherMetricsEntity data, WHEN deleteWeatherMetrics is called, THEN data is deleted correctly`() =
        runBlocking {
            weatherLocalStorage.deleteWeatherMetrics(weatherMetricsEntity)
            verify(weatherMetricsDao).deleteWeatherMetrics(weatherMetricsEntity)
        }

    companion object {
        private val conditionForecastEntity = ConditionForecastEntity(
            id = 1,
            condition = "Sunny",
            minTemperature = 25
        )
        private val hourInfoEntity = HourInfoEntity(
            id = 1,
            hour = 2,
            temp = 22,
            image = "",
            chanceOfRain = 25
        )
        private val conditionForecastWithHours = ConditionForecastWithHours(
            conditionForecast = conditionForecastEntity,
            hoursInfo = listOf(hourInfoEntity)
        )

        private val currentWeatherEntity = CurrentWeatherEntity(
            realTemperature = 1,
            description = "Sunny",
            descriptionImage = "",
            feelsLikeTemperature = 10
        )

        private val dailyWeatherForecastEntity = DailyWeatherForecastEntity(
            id = 1,
            day = 1,
            chanceOfRain = 25,
            minConditionImage = "",
            maxConditionImage = "",
            minTemp = 10,
            maxTemp = 25
        )

        private val weatherMetricsEntity = WeatherMetricsEntity(
            id = 1,
            uvIndex = "Clear",
            humidity = 20.8,
            wind = 5.2,
            sunrise = "06:00 AM",
            sunset = "07:00 PM"
        )
    }
}

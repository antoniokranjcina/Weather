package com.antoniok.core.data_source.local

import com.antoniok.core.data_source.local.database.dao.WeatherDao
import com.antoniok.core.data_source.local.database.entity.WeatherEntity
import com.antoniok.core.data_source.local.database.entity.WeatherWithDaysAndHours
import com.antoniok.core.data_source.local.database.entity.current.CurrentEntity
import com.antoniok.core.data_source.local.database.entity.forecast.AstroEntity
import com.antoniok.core.data_source.local.database.entity.forecast.DayEntity
import com.antoniok.core.data_source.local.database.entity.forecast.ForecastDayEntity
import com.antoniok.core.data_source.local.database.entity.forecast.HourEntity
import com.antoniok.core.data_source.local.database.entity.location.LocationEntity
import com.antoniok.core.data_source.local.database.entity.shared.ConditionEntity
import com.antoniok.core.data_source.local.preferences.LocalDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class WeatherLocalStorageTest {

    private lateinit var weatherLocalStorage: WeatherLocalDataSource

    @Mock
    private lateinit var weatherDao: WeatherDao

    @Mock
    private lateinit var localDataStore: LocalDataStore

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        weatherLocalStorage = WeatherLocalStorage(weatherDao, localDataStore)
    }

    @Test
    fun `GIVEN a weather entity WHEN insertWeather is called THEN it should insert the weather`() =
        runBlocking {
            weatherLocalStorage.insertWeather(weatherEntity1)
            verify(weatherDao).insertWeather(weatherEntity1)
        }

    @Test
    fun `GIVEN a hour entities WHEN insertHours is called THEN it should insert the hours`() =
        runBlocking {
            weatherLocalStorage.insertHours(hourEntities)
            verify(weatherDao).insertHours(hourEntities)
        }

    @Test
    fun `GIVEN a forecast day entities WHEN insertForecastDays is called THEN it should insert the forecastDay`() =
        runBlocking {
            weatherLocalStorage.insertForecastDays(forecastDayEntities)
            verify(weatherDao).insertForecastDays(forecastDayEntities)
        }

    @Test
    fun `GIVEN a city WHEN getWeatherByCity is called THEN it should return the weather for that city`() =
        runBlocking {
            val city = "Zagreb"
            `when`(weatherDao.getWeatherByCity(city)).thenReturn(flowOf(weatherWithDaysAndHours1))

            val weather = weatherLocalStorage.getWeatherByCity(city)

            assertNotNull(weather)
            assertEquals(weatherWithDaysAndHours1, weather.single())
        }

    @Test
    fun `GIVEN weather entities WHEN weathers is called THEN it should return a list of weather entities`() =
        runBlocking {
            val weatherEntities = listOf(weatherWithDaysAndHours1, weatherWithDaysAndHours2)
            `when`(weatherDao.getAllWeathers()).thenReturn(flowOf(weatherEntities))

            val weathers = weatherLocalStorage.weathers.single()

            assert(weathers.isNotEmpty())
            assertEquals(weatherEntities, weathers)

        }

    @Test
    fun `GIVEN a weather entity WHEN deleteWeather is called THEN it should delete the weather`() =
        runBlocking {
            weatherLocalStorage.deleteWeather(weatherEntity1)
            verify(weatherDao).deleteWeather(weatherEntity1)
        }

    @Test
    fun `GIVEN WHEN deleteAllWeathers is called THEN it should delete all weather entities`() {
        runBlocking {
            weatherLocalStorage.deleteAllWeathers()
            verify(weatherDao).deleteAllWeathers()
        }
    }

    @Test
    fun `GIVEN saveIndex is called WHEN an index is provided THEN it should be saved`() =
        runBlocking {
            weatherLocalStorage.saveIndex(11)
            verify(localDataStore).saveIndex(11)
        }

    @Test
    fun `GIVEN index flow is requested WHEN data is available THEN it should return the flow`() =
        runBlocking {
            `when`(localDataStore.index).thenReturn(flowOf(1))
            val result = localDataStore.index.first()
            assertEquals(result, 1)
        }

    companion object {
        private val locationEntity = LocationEntity(
            name = "Zagreb",
            region = "Grad Zagreb",
            country = "Croatia",
            localtimeEpoch = 123,
        )

        private val conditionEntity = ConditionEntity(
            text = "Clear",
            code = 1,
            icon = "www.image.com"
        )

        private val currentEntity = CurrentEntity(
            lastUpdatedEpoch = 123,
            tempC = 20.5,
            tempF = 20.5,
            isDay = 1,
            condition = conditionEntity,
            windMph = 12.5,
            windKph = 12.5,
            windDegree = 90,
            windDir = "south",
            pressureMb = 11.1,
            pressureIn = 89.1,
            humidity = 98,
            cloud = 1,
            feelsLikeC = 20.1,
            feelsLikeF = 20.1,
            uv = 1,
        )

        private val dayEntity = DayEntity(
            maxTempC = 23.1,
            maxTempF = 23.1,
            minTempC = 12.1,
            minTempF = 12.1,
            conditionText = conditionEntity.text,
            conditionIcon = conditionEntity.icon,
            conditionCode = conditionEntity.code,
        )

        private val astroEntity = AstroEntity(
            sunrise = "05:12",
            sunset = "05:12",
            moonrise = "05:12",
            moonset = "05:12",
        )

        private val weatherEntity1 = WeatherEntity(
            id = "Zagreb",
            location = locationEntity,
            current = currentEntity,
            day = dayEntity,
            astro = astroEntity
        )

        private val weatherEntity2 = WeatherEntity(
            id = "New York",
            location = locationEntity,
            current = currentEntity,
            day = dayEntity,
            astro = astroEntity
        )

        val weatherWithDaysAndHours1 = WeatherWithDaysAndHours(
            weather = weatherEntity1,
            hours = emptyList(),
            forecastDays = emptyList()
        )

        val weatherWithDaysAndHours2 = WeatherWithDaysAndHours(
            weather = weatherEntity2,
            hours = emptyList(),
            forecastDays = emptyList()
        )

        val hourEntities = listOf(
            HourEntity(
                isDay = true,
                hourId = "",
                id = "",
                hour = 1,
                feelsLikeC = 20.1,
                tempC = 23.1,
                chanceOfRain = 80,
                conditionText = "rainy",
                conditionIcon = "www.image.com",
                conditionCode = 1230,
            )
        )

        val forecastDayEntities = listOf(
            ForecastDayEntity(
                forecastDayId = "",
                id = "",
                day = 2,
                chanceOfRain = 82,
                maxTempC = 12.2,
                maxTempF = 32.1,
                minTempC = 32.1,
                minTempF = 12.2,
                minConditionText = "Rainy",
                minConditionIcon = "www.image.com",
                minConditionCode = 100,
                maxConditionText = "Sun",
                maxConditionIcon = "www.image.com",
                maxConditionCode = 1200,
            )
        )

    }

}

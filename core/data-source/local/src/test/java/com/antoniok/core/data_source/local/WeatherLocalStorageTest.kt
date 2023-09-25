package com.antoniok.core.data_source.local

import com.antoniok.core.data_source.local.dao.WeatherDao
import com.antoniok.core.data_source.local.entity.WeatherEntity
import com.antoniok.core.data_source.local.entity.current.CurrentEntity
import com.antoniok.core.data_source.local.entity.forecast.AstroEntity
import com.antoniok.core.data_source.local.entity.forecast.DayEntity
import com.antoniok.core.data_source.local.entity.location.LocationEntity
import com.antoniok.core.data_source.local.entity.shared.ConditionEntity
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

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        weatherLocalStorage = WeatherLocalStorage(weatherDao)
    }

    @Test
    fun `GIVEN a weather entity WHEN insertWeather is called THEN it should insert the weather`() =
        runBlocking {
            weatherLocalStorage.insertWeather(weatherEntity1)
            verify(weatherDao).insertWeather(weatherEntity1)
        }

    @Test
    fun `GIVEN a city WHEN getWeatherByCity is called THEN it should return the weather for that city`() =
        runBlocking {
            val city = "Zagreb"
            `when`(weatherDao.getWeatherByCity(city)).thenReturn(flowOf(weatherEntity1))

            val weather = weatherLocalStorage.getWeatherByCity(city)

            assertNotNull(weather)
            assertEquals(weatherEntity1, weather.single())
        }

    @Test
    fun `GIVEN weather entities WHEN weathers is called THEN it should return a list of weather entities`() =
        runBlocking {
            val weatherEntities = listOf(weatherEntity1, weatherEntity2)
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

        val weatherEntity1 = WeatherEntity(
            id = "Zagreb",
            location = locationEntity,
            current = currentEntity,
            day = dayEntity,
            astro = astroEntity
        )

        val weatherEntity2 = WeatherEntity(
            id = "New York",
            location = locationEntity,
            current = currentEntity,
            day = dayEntity,
            astro = astroEntity
        )
    }

}

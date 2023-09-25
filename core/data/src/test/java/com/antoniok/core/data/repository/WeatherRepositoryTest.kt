package com.antoniok.core.data.repository

import com.antoniok.core.data_source.local.WeatherLocalDataSource
import com.antoniok.core.data_source.local.entity.WeatherEntity
import com.antoniok.core.data_source.local.entity.asExternalModule
import com.antoniok.core.data_source.local.entity.current.CurrentEntity
import com.antoniok.core.data_source.local.entity.forecast.AstroEntity
import com.antoniok.core.data_source.local.entity.forecast.DayEntity
import com.antoniok.core.data_source.local.entity.location.LocationEntity
import com.antoniok.core.data_source.local.entity.shared.ConditionEntity
import com.antoniok.weather.data_source.remote.WeatherNetworkDataSource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class WeatherRepositoryTest {

    @Mock
    private lateinit var localDataSource: WeatherLocalDataSource

    @Mock
    private lateinit var networkDataSource: WeatherNetworkDataSource

    private lateinit var repository: WeatherRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = OfflineFirstWeatherRepository(localDataSource, networkDataSource)
    }

    @Test
    fun `GIVEN a city WHEN getWeatherByCity is called THEN it returns weather from local data source`() =
        runBlocking {
            val city = "Zagreb"
            `when`(localDataSource.getWeatherByCity(city)).thenReturn(flowOf(weatherEntity1))

            val weather = repository.getWeatherByCity(city).single()

            assertNotNull(weather)
            assertEquals(weatherEntity1.asExternalModule(), weather)
        }

    @Test
    fun `GIVEN a list of weather entities WHEN getWeathers is called THEN it returns a list of weather from local data source`() =
        runBlocking {
            val weatherEntities = listOf(weatherEntity1, weatherEntity2)
            `when`(localDataSource.weathers).thenReturn(flowOf(weatherEntities))

            val weathers = repository.weathers.single()

            assertFalse(weathers.isEmpty())
            assertEquals(weatherEntities.map { it.asExternalModule() }, weathers)
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

package com.antoniok.weather.data_source.remote

import com.antoniok.weather.data_source.remote.model.WeatherDto
import com.antoniok.weather.data_source.remote.model.alerts.AlertDto
import com.antoniok.weather.data_source.remote.model.alerts.AlertsDto
import com.antoniok.weather.data_source.remote.model.current.CurrentDto
import com.antoniok.weather.data_source.remote.model.forecast.AstroDto
import com.antoniok.weather.data_source.remote.model.forecast.DayDto
import com.antoniok.weather.data_source.remote.model.forecast.ForecastDayDto
import com.antoniok.weather.data_source.remote.model.forecast.ForecastDto
import com.antoniok.weather.data_source.remote.model.location.LocationDto
import com.antoniok.weather.data_source.remote.model.shared.AirQualityDto
import com.antoniok.weather.data_source.remote.model.shared.ConditionDto
import com.antoniok.weather.data_source.remote.resource.NetworkResource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class RetrofitWeatherNetworkTest {

    @Mock
    private lateinit var weatherDataSource: WeatherNetworkDataSource

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `GIVEN a valid city WHEN getWeather is called THEN it returns the location`() =
        runBlocking {
            val city = "New York"
            val days = 1
            val dataSourceResponse = NetworkResource.Success(weatherDto)

            `when`(weatherDataSource.getWeather(city, days)).thenReturn(dataSourceResponse)
            val result = weatherDataSource.getWeather(city, days)

            assert(result is NetworkResource.Success)
            assert((result as NetworkResource.Success).data == weatherDto)
        }

    @Test
    fun `GIVEN an invalid city WHEN getWeather is called THEN it returns an error`() =
        runBlocking {
            val city = ""
            val exception = Exception("404")
            val errorResponse = NetworkResource.Error<WeatherDto>(exception)

            `when`(weatherDataSource.getWeather(city, 1)).thenReturn(errorResponse)
            val result = weatherDataSource.getWeather(city, 1)

            assert(result is NetworkResource.Error)
            assert((result as NetworkResource.Error).exception.message == "404")
        }

    companion object {
        private val conditionDto = ConditionDto(
            text = "Partly Cloudy",
            icon = "https://example.com/partly-cloudy.png",
            code = 802
        )

        private val airQualityDto = AirQualityDto(
            co = 3.2,
            no2 = 7.8,
            o3 = 42.1,
            so2 = 1.5,
            pm25 = 8.7,
            pm10 = 15.4,
            usEpaIndex = 34,
            gbDefraIndex = 42
        )

        private val dayDto = DayDto(
            maxTempC = 28.5,
            maxTempF = 83.3,
            minTempC = 16.7,
            minTempF = 62.1,
            avgTempC = 23.9,
            avgTempF = 75.0,
            maxWindMph = 8.6,
            maxWindKph = 13.8,
            totalPrecipMm = 0.5,
            totalPrecipIn = 0.02,
            totalSnowCm = 0,
            avgVisKm = 10.0,
            avgVisMiles = 6.2,
            avgHumidity = 65.6,
            dailyWillItRain = 0,
            dailyChanceOfRain = 0,
            dailyWillItSnow = 0,
            dailyChanceOfSnow = 0,
            condition = conditionDto,
            uv = 5.0,
            airQuality = airQualityDto
        )

        private val astroDto = AstroDto(
            sunrise = "06:23 AM",
            sunset = "08:12 PM",
            moonrise = "03:41 AM",
            moonset = "06:54 PM",
            moonPhase = "Waxing Crescent",
            moonIllumination = "23%",
            isMoonUp = 1,
            isSunUp = 1
        )

        private val locationDto = LocationDto(
            name = "New York City",
            region = "New York",
            country = "United States",
            lat = 40.7128,
            lon = -74.0060,
            tzId = "America/New_York",
            localtimeEpoch = 1695396213,
            localtime = "2023-09-22 11:23 AM"
        )

        private val currentDto = CurrentDto(
            lastUpdatedEpoch = 1695396213,
            lastUpdated = "2023-09-22 11:23 AM",
            tempC = 28.5,
            tempF = 83.3,
            isDay = 1,
            condition = conditionDto,
            windMph = 5.8,
            windKph = 9.3,
            windDegree = 90,
            windDir = "East",
            pressureMb = 1013.2,
            pressureIn = 29.92,
            precipMm = 0.0,
            precipIn = 0.0,
            humidity = 65,
            cloud = 20,
            feelsLikeC = 27.0,
            feelsLikeF = 80.6,
            visKm = 10.2,
            visMiles = 6.1,
            uv = 5,
            gustMph = 10.2,
            gustKph = 16.5,
            airQuality = airQualityDto
        )

        private val forecastsDto = listOf(
            ForecastDayDto(
                date = "2023-09-23",
                dateEpoch = 1695472612,
                day = dayDto,
                astro = astroDto,
                hour = emptyList()
            )
        )

        private val alertsDto = listOf(
            AlertDto(
                headline = "Severe Thunderstorm Warning",
                messageType = "Alert",
                severity = "Severe",
                urgency = "Immediate",
                areas = "New York City",
                category = "Weather",
                certainty = "Likely",
                event = "Severe Thunderstorm",
                note = "Seek shelter immediately.",
                effective = "2023-09-22 01:30 PM",
                expires = "2023-09-22 02:30 PM",
                desc = "A severe thunderstorm is approaching.",
                instruction = "Stay indoors and away from windows."
            )
        )

        private val weatherDto = WeatherDto(
            location = locationDto,
            current = currentDto,
            forecast = ForecastDto(
                forecastDay = forecastsDto,
            ),
            alerts = AlertsDto(
                alerts = alertsDto
            )
        )
    }

}

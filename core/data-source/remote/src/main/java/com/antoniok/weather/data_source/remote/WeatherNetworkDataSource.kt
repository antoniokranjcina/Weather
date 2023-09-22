package com.antoniok.weather.data_source.remote

import com.antoniok.weather.data_source.remote.model.alerts.AlertDto
import com.antoniok.weather.data_source.remote.model.current.CurrentDto
import com.antoniok.weather.data_source.remote.model.forecast.ForecastDayDto
import com.antoniok.weather.data_source.remote.model.location.LocationDto
import com.antoniok.weather.data_source.remote.resource.NetworkResource

/**
 * Interface representing network calls to the Weather backend
 */
interface WeatherNetworkDataSource {
    /**
     * Retrieves the current location information for the specified city from the weather network.
     *
     * @param city The name of the city for which location information is requested.
     * @return A [NetworkResource] containing the [LocationDto] representing the location data.
     */
    suspend fun getLocation(city: String): NetworkResource<LocationDto>

    /**
     * Retrieves the current weather data for the specified city from the weather network.
     *
     * @param city The name of the city for which weather data is requested.
     * @return A [NetworkResource] containing the [CurrentDto] representing the current weather
     * conditions.
     */
    suspend fun getCurrent(city: String): NetworkResource<CurrentDto>

    /**
     * Retrieves the weather forecast data for the specified city for a specified number of days
     * from the weather network.
     *
     * @param city The name of the city for which the weather forecast is requested.
     * @param days The number of days for which the forecast is requested.
     * @return A [NetworkResource] containing a list of [ForecastDayDto] representing the weather
     * forecast.
     */
    suspend fun getForecast(city: String, days: Int): NetworkResource<List<ForecastDayDto>>

    /**
     * Retrieves weather alerts and warnings for the specified city from the weather network.
     *
     * @param city The name of the city for which weather alerts are requested.
     * @return A [NetworkResource] containing a list of [AlertDto] representing weather alerts and
     * warnings.
     */
    suspend fun getAlerts(city: String): NetworkResource<List<AlertDto>>

}

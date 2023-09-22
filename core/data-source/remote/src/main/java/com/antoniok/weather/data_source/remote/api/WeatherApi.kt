package com.antoniok.weather.data_source.remote.api

import com.antoniok.weather.core.data_source.remote.BuildConfig
import com.antoniok.weather.data_source.remote.WeatherNetworkDataSource
import com.antoniok.weather.data_source.remote.model.alerts.AlertDto
import com.antoniok.weather.data_source.remote.model.alerts.WeatherAlertDto
import com.antoniok.weather.data_source.remote.model.current.CurrentDto
import com.antoniok.weather.data_source.remote.model.current.WeatherCurrentDto
import com.antoniok.weather.data_source.remote.model.forecast.ForecastDayDto
import com.antoniok.weather.data_source.remote.model.forecast.WeatherForecastDto
import com.antoniok.weather.data_source.remote.model.location.LocationDto
import com.antoniok.weather.data_source.remote.model.location.WeatherLocationDto
import com.antoniok.weather.data_source.remote.resource.NetworkResource
import com.antoniok.weather.data_source.remote.resource.networkRequest
import okhttp3.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private interface WeatherApi {

    @GET("current.json")
    suspend fun getLocation(
        @Query("q") city: String
    ): Response<WeatherLocationDto>

    @GET("current.json")
    suspend fun getCurrent(
        @Query("q") city: String,
        @Query("aqi") airQualityData: String = "no",
    ): Response<WeatherCurrentDto>

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("days") days: Int = 1,
        @Query("aqi") airQualityData: String = "yes",
    ): Response<WeatherForecastDto>

    @GET("forecast.json")
    suspend fun getAlert(
        @Query("q") city: String,
        @Query("alerts") alerts: String = "yes"
    ): Response<WeatherAlertDto>

}

/**
 * Wrapper for data provided from the [WeatherApi]
 */
internal class RetrofitWeatherNetwork(
    okHttpCallFactory: Call.Factory
) : WeatherNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(BuildConfig.WEATHER_URL)
        .callFactory(okHttpCallFactory)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    override suspend fun getLocation(city: String): NetworkResource<LocationDto> =
        networkApi.getLocation(city = city).networkRequest(
            onSuccess = {
                NetworkResource.Success(it.location)
            },
            onError = {
                NetworkResource.Error(it)
            }
        )

    override suspend fun getCurrent(city: String): NetworkResource<CurrentDto> =
        networkApi.getCurrent(city = city).networkRequest(
            onSuccess = {
                NetworkResource.Success(it.current)
            },
            onError = {
                NetworkResource.Error(it)
            }
        )

    override suspend fun getForecast(
        city: String,
        days: Int
    ): NetworkResource<List<ForecastDayDto>> =
        networkApi.getForecast(city, days = days).networkRequest(
            onSuccess = {
                NetworkResource.Success(it.forecast.forecastDay)
            },
            onError = {
                NetworkResource.Error(it)
            }
        )

    override suspend fun getAlerts(city: String): NetworkResource<List<AlertDto>> =
        networkApi.getAlert(city = city).networkRequest(
            onSuccess = {
                NetworkResource.Success(it.alertDto.alerts)
            },
            onError = {
                NetworkResource.Error(it)
            }
        )

}

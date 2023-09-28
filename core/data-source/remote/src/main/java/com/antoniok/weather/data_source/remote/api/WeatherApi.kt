package com.antoniok.weather.data_source.remote.api

import com.antoniok.weather.core.data_source.remote.BuildConfig
import com.antoniok.weather.data_source.remote.WeatherNetworkDataSource
import com.antoniok.weather.data_source.remote.model.WeatherDto
import com.antoniok.weather.data_source.remote.model.location.SearchedLocationDto
import com.antoniok.weather.data_source.remote.resource.NetworkResource
import com.antoniok.weather.data_source.remote.resource.networkRequest
import okhttp3.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private interface WeatherApi {

    @GET("forecast.json")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("days") days: Int
    ): Response<WeatherDto>

    @GET("search.json")
    suspend fun getLocation(
        @Query("q") location: String
    ): Response<List<SearchedLocationDto>>

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

    override suspend fun getWeather(city: String, days: Int): NetworkResource<WeatherDto> =
        networkApi.getWeather(city = city, days = days).networkRequest(
            onSuccess = {
                NetworkResource.Success(it)
            },
            onError = {
                it.printStackTrace()
                NetworkResource.Error(it)
            }
        )

    override suspend fun getLocations(location: String): NetworkResource<List<SearchedLocationDto>> =
        networkApi.getLocation(location = location).networkRequest(
            onSuccess = {
                NetworkResource.Success(it)
            },
            onError = {
                it.printStackTrace()
                NetworkResource.Error(it)
            }
        )
}

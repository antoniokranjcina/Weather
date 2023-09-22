package com.antoniok.weather.data_source.remote.di

import com.antoniok.weather.core.data_source.remote.BuildConfig
import com.antoniok.weather.data_source.remote.WeatherNetworkDataSource
import com.antoniok.weather.data_source.remote.api.RetrofitWeatherNetwork
import okhttp3.Call
import okhttp3.OkHttpClient
import org.koin.dsl.module

private const val API_KEY = "key"

val networkModule = module {

    single {
        provideWeatherNetwork(call = provideOkHttpFactory())
    }

}

private fun provideWeatherNetwork(call: Call.Factory): WeatherNetworkDataSource =
    RetrofitWeatherNetwork(call)

private fun provideOkHttpFactory() = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        val url = originalHttpUrl
            .newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.WEATHER_API_KEY)
            .build()
        request.url(url)
        return@addInterceptor chain.proceed(request.build())
    }
    .build()

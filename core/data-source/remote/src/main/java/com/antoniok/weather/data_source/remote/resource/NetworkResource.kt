package com.antoniok.weather.data_source.remote.resource

sealed class NetworkResource<T> {
    class Success<T>(val data: T) : NetworkResource<T>()
    class Error<T>(val exception: Exception) : NetworkResource<T>()
}

package com.antoniok.core.data.model

sealed class Resource<T>(val data: T? = null) {
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Error(val exception: Exception) : Resource<Nothing>()
}

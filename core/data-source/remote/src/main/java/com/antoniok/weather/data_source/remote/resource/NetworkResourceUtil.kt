package com.antoniok.weather.data_source.remote.resource

import retrofit2.Response

private const val EMPTY_RESPONSE = "Api response is empty."

// TODO make custom exceptions
internal inline fun <T, R> Response<T>.networkRequest(
    onSuccess: (T) -> R,
    onError: (Exception) -> R
): R = if (isSuccessful) {
    body()?.let {
        onSuccess(it)
    } ?: run {
        onError(Exception(EMPTY_RESPONSE))
    }
} else {
    onError(Exception(code().toString()))
}

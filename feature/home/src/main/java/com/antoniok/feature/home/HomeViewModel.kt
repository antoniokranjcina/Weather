package com.antoniok.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.antoniok.feature.home.data.dummyConditionForecast
import com.antoniok.feature.home.data.dummyCurrentWeather
import com.antoniok.feature.home.data.dummyDailyWeatherForecasts
import com.antoniok.feature.home.data.dummyWeatherMetrics

class HomeViewModel : ViewModel() {

    var currentWeather by mutableStateOf(dummyCurrentWeather)
        private set

    var conditionForecast by mutableStateOf(dummyConditionForecast)
        private set

    var weatherMetrics by mutableStateOf(dummyWeatherMetrics)
        private set

    var dailyWeatherForecasts by mutableStateOf(dummyDailyWeatherForecasts)
        private set

}

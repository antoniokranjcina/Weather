package com.antoniok.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.antoniok.core.model.dummyConditionForecast
import com.antoniok.core.model.dummyCurrentWeather
import com.antoniok.core.model.dummyDailyWeatherForecasts
import com.antoniok.core.model.dummyWeatherMetrics

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

package com.antoniok.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.antoniok.core.ui.spacing.Spacing
import com.antoniok.feature.home.component.CurrentWeatherInfo
import com.antoniok.feature.home.component.daily.DailyWeather
import com.antoniok.feature.home.component.grid.WeatherInfoGrid
import com.antoniok.feature.home.component.weekly.WeeklyWeather
import com.antoniok.feature.home.data.CurrentWeather
import com.antoniok.feature.home.data.DailyWeatherForecast
import com.antoniok.feature.home.data.HourInfo
import com.antoniok.feature.home.data.WeatherMetrics
import com.antoniok.feature.home.data.dummyCurrentWeather
import com.antoniok.feature.home.data.dummyDailyWeatherForecasts
import com.antoniok.feature.home.data.dummyHourInfoList
import com.antoniok.feature.home.data.dummyWeatherMetrics
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = getViewModel()
) {
    HomeScreenContent(
        modifier = modifier,
        currentWeather = homeViewModel.currentWeather,
        condition = homeViewModel.conditionForecast.condition,
        minTemperature = homeViewModel.conditionForecast.minTemperature,
        hourInfo = homeViewModel.conditionForecast.hours,
        weatherMetrics = homeViewModel.weatherMetrics,
        dailyWeatherForecasts = homeViewModel.dailyWeatherForecasts
    )
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    currentWeather: CurrentWeather,
    condition: String,
    minTemperature: Int,
    hourInfo: List<HourInfo>,
    weatherMetrics: WeatherMetrics,
    dailyWeatherForecasts: List<DailyWeatherForecast>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Spacing.m)
    ) {
        CurrentWeatherInfo(
            temp = currentWeather.realTemp,
            description = currentWeather.description,
            descriptionImage = currentWeather.descriptionImage,
            city = currentWeather.city,
            feelsLikeTemp = currentWeather.feelsLikeTemp
        )
        Spacer(modifier = Modifier.size(Spacing.xl))
        DailyWeather(
            condition = condition,
            minTemp = minTemperature,
            hourInfo = hourInfo
        )
        Spacer(modifier = Modifier.size(Spacing.l))
        WeatherInfoGrid(weatherMetrics = weatherMetrics)
        Spacer(modifier = Modifier.size(Spacing.l))
        WeeklyWeather(days = dailyWeatherForecasts)
    }
}

@Preview
@Composable
private fun HomeScreenContentPreview() {
    HomeScreenContent(
        currentWeather = dummyCurrentWeather,
        condition = "Rainy",
        minTemperature = 10,
        hourInfo = dummyHourInfoList,
        weatherMetrics = dummyWeatherMetrics,
        dailyWeatherForecasts = dummyDailyWeatherForecasts
    )
}

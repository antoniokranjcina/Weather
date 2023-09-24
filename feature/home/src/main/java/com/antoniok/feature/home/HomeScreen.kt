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
import com.antoniok.core.model.CurrentWeather
import com.antoniok.core.model.DailyWeatherForecast
import com.antoniok.core.model.HourInfo
import com.antoniok.core.model.WeatherMetrics
import com.antoniok.core.model.dummyCurrentWeather
import com.antoniok.core.model.dummyDailyWeatherForecasts
import com.antoniok.core.model.dummyHourInfoList
import com.antoniok.core.model.dummyWeatherMetrics
import com.antoniok.core.ui.spacing.Spacing
import com.antoniok.feature.home.component.CurrentWeatherInfo
import com.antoniok.feature.home.component.daily.DailyWeather
import com.antoniok.feature.home.component.grid.WeatherInfoGrid
import com.antoniok.feature.home.component.weekly.WeeklyWeather
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
            city = "Zagreb", // TODO hardcoded
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

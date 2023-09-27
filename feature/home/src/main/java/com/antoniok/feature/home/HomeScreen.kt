package com.antoniok.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.antoniok.core.data_source.local.database.util.getTime
import com.antoniok.core.model.Weather
import com.antoniok.core.model.dummyWeather
import com.antoniok.core.ui.spacing.Spacing
import com.antoniok.feature.home.component.CurrentWeatherInfo
import com.antoniok.feature.home.component.daily.DailyWeather
import com.antoniok.feature.home.component.grid.WeatherInfoGrid
import com.antoniok.feature.home.component.weekly.WeeklyWeather
import com.antoniok.weather.feature.home.R
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = getViewModel()
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    when (uiState) {
        is WeatherUiState.Success -> {
            val data = (uiState as WeatherUiState.Success).weather
            HomeScreenContent(
                modifier = modifier,
                weather = data
            )
        }
        // TODO put spinning wheel
        WeatherUiState.Empty -> {}
        WeatherUiState.Loading -> {}
    }
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    weather: Weather
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Spacing.m)
    ) {
        CurrentWeatherInfo(
            temp = weather.current.tempC.toInt(),
            description = weather.current.condition.text,
            imageCode = weather.current.condition.code,
            city = weather.location.name,
            feelsLikeTemp = weather.current.feelsLikeC.toInt()
        )
        if (weather.hours.isNotEmpty()) {
            Spacer(modifier = Modifier.size(Spacing.xl))
            DailyWeather(
                condition = weather.current.condition.text,
                minTemp = weather.day.minTempC.toInt(),
                hours = weather.hours
            )
        }
        Spacer(modifier = Modifier.size(Spacing.l))
        if (weather.days.isNotEmpty()) {
            WeeklyWeather(days = weather.days)
        }
        Spacer(modifier = Modifier.size(Spacing.l))
        WeatherInfoGrid(
            current = weather.current,
            astro = weather.astro
        )
        Spacer(modifier = Modifier.size(Spacing.l))
        Text(
            text = stringResource(
                id = R.string.updated_at,
                weather.current.lastUpdatedEpoch.getTime()
            ),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
private fun HomeScreenContentPreview() {
    HomeScreenContent(weather = dummyWeather)
}

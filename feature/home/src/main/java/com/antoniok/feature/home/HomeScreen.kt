package com.antoniok.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.antoniok.core.model.Astro
import com.antoniok.core.model.Current
import com.antoniok.core.model.Day
import com.antoniok.core.model.Location
import com.antoniok.core.model.dummyAstro
import com.antoniok.core.model.dummyCurrent
import com.antoniok.core.model.dummyDay
import com.antoniok.core.model.dummyLocation
import com.antoniok.core.ui.spacing.Spacing
import com.antoniok.feature.home.component.CurrentWeatherInfo
import com.antoniok.feature.home.component.grid.WeatherInfoGrid
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
                location = data.location,
                current = data.current,
                astro = data.astro,
                day = data.day
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
    location: Location,
    current: Current,
    astro: Astro,
    day: Day
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Spacing.m)
    ) {
        CurrentWeatherInfo(
            temp = current.tempC.toInt(), // TODO should not be done here
            description = current.condition.text,
            descriptionImage = current.condition.icon,
            city = location.name,
            feelsLikeTemp = current.feelsLikeC.toInt()
        )
        Spacer(modifier = Modifier.size(Spacing.xl))
//        DailyWeather(
//            condition = current.condition.text,
//            minTemp = day.minTempC.toInt(),
//            hourInfo = emptyList()
//        )
        Spacer(modifier = Modifier.size(Spacing.l))
        WeatherInfoGrid(
            current = current,
            astro = astro
        )
        Spacer(modifier = Modifier.size(Spacing.l))
//        WeeklyWeather(days = dailyWeatherForecasts)
    }
}

@Preview
@Composable
private fun HomeScreenContentPreview() {
    HomeScreenContent(
        location = dummyLocation,
        current = dummyCurrent,
        astro = dummyAstro,
        day = dummyDay
    )
}

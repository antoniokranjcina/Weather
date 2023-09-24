package com.antoniok.feature.home.component.weekly

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.antoniok.core.ui.spacing.Spacing
import com.antoniok.feature.home.data.DailyWeatherForecast
import com.antoniok.feature.home.data.dummyDailyWeatherForecasts

@Composable
fun WeeklyWeather(
    modifier: Modifier = Modifier,
    days: List<DailyWeatherForecast>
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(Spacing.l)
        ) {
            days.forEach {
                DailyItem(
                    day = it.day,
                    chanceOfRain = it.chanceOfRain,
                    minConditionImage = it.minConditionImage,
                    maxConditionImage = it.maxConditionImage,
                    minTemp = it.minTemp,
                    maxTemp = it.maxTemp
                )
            }
        }
    }
}

@Preview
@Composable
private fun WeeklyWeatherPreview() {
    WeeklyWeather(
        days = dummyDailyWeatherForecasts
    )
}

package com.antoniok.feature.home.component.weekly

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.antoniok.core.model.ForecastDay
import com.antoniok.core.model.dummyDays
import com.antoniok.core.ui.spacing.Spacing

@Composable
fun WeeklyWeather(
    modifier: Modifier = Modifier,
    days: List<ForecastDay>
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(Spacing.l)
        ) {
            days.forEach {
                DailyItem(
                    day = it.day.toString(), // TODO
                    chanceOfRain = it.chanceOfRain.toInt(),
                    minConditionImage = it.minCondition.icon,
                    maxConditionImage = it.maxCondition.icon,
                    minTemp = it.minTempC.toInt(),
                    maxTemp = it.maxTempC.toInt()
                )
            }
        }
    }
}

@Preview
@Composable
private fun WeeklyWeatherPreview() {
    WeeklyWeather(days = dummyDays)
}

package com.antoniok.feature.home.component.weekly

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.antoniok.core.model.ForecastDay
import com.antoniok.core.model.dummyDays
import com.antoniok.core.ui.spacing.Spacing
import com.antoniok.feature.home.util.toDay
import com.antoniok.weather.feature.home.R

@Composable
fun WeeklyWeather(
    modifier: Modifier = Modifier,
    days: List<ForecastDay>
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(Spacing.l)
        ) {
            days.forEachIndexed { index, day ->
                DailyItem(
                    day = stringResource(
                        id = if (index == 0) {
                            R.string.today
                        } else {
                            day.day.toDay()
                        }
                    ),
                    chanceOfRain = day.chanceOfRain,
                    minConditionCode = day.minCondition.code,
                    maxConditionCode = day.maxCondition.code,
                    minTemp = day.minTempC.toInt(),
                    maxTemp = day.maxTempC.toInt()
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

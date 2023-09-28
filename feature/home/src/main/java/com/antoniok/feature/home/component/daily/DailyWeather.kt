package com.antoniok.feature.home.component.daily

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.antoniok.core.model.Hour
import com.antoniok.core.model.dummyHours
import com.antoniok.core.ui.spacing.Spacing
import com.antoniok.weather.feature.home.R
import java.util.Calendar

@Composable
fun DailyWeather(
    modifier: Modifier = Modifier,
    condition: String,
    minTemp: Int,
    hours: List<Hour>
) {
    Card(modifier = modifier) {
        Column {
            Text(
                modifier = Modifier.padding(top = Spacing.l, start = Spacing.l),
                text = stringResource(id = R.string.min_temperature, condition, minTemp)
            )
            LazyRow(
                modifier = Modifier.padding(Spacing.l),
            ) {
                // Find the index of the current hour in the dailyInfo list
                val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                val initialItemIndex = hours.indexOfFirst { it.hour == currentHour }

                // Rotate the dailyInfo list to start with the current hour's item
                val rotated = hours.drop(initialItemIndex) + hours.take(initialItemIndex)
                items(rotated) {
                    HourItem(
                        isDay = it.isDay,
                        hour = it.hour,
                        imageCode = it.condition.code,
                        temp = it.tempC.toInt(),
                        chanceOfRain = it.chanceOfRain
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun WholeDayPreview() {
    DailyWeather(
        condition = "Rainy",
        minTemp = 22,
        hours = dummyHours
    )
}

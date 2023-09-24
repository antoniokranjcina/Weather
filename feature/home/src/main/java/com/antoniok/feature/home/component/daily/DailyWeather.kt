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
import com.antoniok.core.ui.spacing.Spacing
import com.antoniok.feature.home.data.HourInfo
import com.antoniok.feature.home.data.dummyHourInfoList
import com.antoniok.weather.feature.home.R
import java.util.Calendar

@Composable
fun DailyWeather(
    modifier: Modifier = Modifier,
    condition: String,
    minTemp: Int,
    hourInfo: List<HourInfo>
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
                val initialItemIndex = hourInfo.indexOfFirst { it.hour == currentHour }

                // Rotate the dailyInfo list to start with the current hour's item
                val rotated = hourInfo.drop(initialItemIndex) + hourInfo.take(initialItemIndex)
                items(rotated) {
                    HourItem(
                        hour = it.hour,
                        image = it.image,
                        temp = it.temp,
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
        hourInfo = dummyHourInfoList
    )
}

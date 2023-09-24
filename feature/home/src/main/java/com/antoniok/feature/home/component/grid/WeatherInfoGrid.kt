package com.antoniok.feature.home.component.grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.antoniok.core.ui.icon.WeatherIcon
import com.antoniok.core.ui.spacing.Spacing
import com.antoniok.feature.home.data.WeatherMetrics
import com.antoniok.feature.home.data.dummyWeatherMetrics
import com.antoniok.weather.feature.home.R

@Composable
fun WeatherInfoGrid(
    modifier: Modifier = Modifier,
    weatherMetrics: WeatherMetrics
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth(0.5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeatherCard(
                image = WeatherIcon.Sun,
                title = R.string.uv_index,
                data = weatherMetrics.uvIndex
            )
            Spacer(modifier = Modifier.size(Spacing.s))
            WeatherCard(
                image = WeatherIcon.Humidity,
                title = R.string.humidity,
                data = "${weatherMetrics.moisture}%"
            )
        }
        Spacer(modifier = Modifier.size(Spacing.s))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeatherCard(
                image = WeatherIcon.Wind,
                title = R.string.wind,
                data = "${weatherMetrics.wind} km/h"
            )
            Spacer(modifier = Modifier.size(Spacing.s))
            SunriseCard(
                sunriseData = weatherMetrics.sunrise,
                sunsetData = weatherMetrics.sunset
            )
        }

    }
}

@Preview
@Composable
private fun WeatherInfoPreview() {
    WeatherInfoGrid(
        weatherMetrics = dummyWeatherMetrics
    )
}

package com.antoniok.feature.home.component.grid

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.antoniok.core.ui.icon.WeatherIcon
import com.antoniok.core.ui.spacing.Spacing
import com.antoniok.weather.feature.home.R

@Composable
private fun Weather(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    @StringRes title: Int,
    data: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(Spacing.m),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(Spacing.xxxl),
            painter = painterResource(id = image),
            contentDescription = null
        )
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = data,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun WeatherCard(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    @StringRes title: Int,
    data: String
) {
    Card(modifier = modifier) {
        Weather(
            image = image,
            title = title,
            data = data
        )
    }
}

@Composable
fun SunriseCard(
    modifier: Modifier = Modifier,
    sunriseData: String,
    sunsetData: String
) {
    Card(modifier = modifier) {
        Row {
            Weather(
                modifier = Modifier.fillMaxWidth(0.5f),
                image = WeatherIcon.Sunrise,
                title = R.string.sunrise,
                data = sunriseData
            )
            Weather(
                modifier = Modifier.fillMaxWidth(),
                image = WeatherIcon.Sunset,
                title = R.string.sunset,
                data = sunsetData
            )
        }
    }
}

@Preview
@Composable
private fun WeatherCardPreview() {
    WeatherCard(
        image = WeatherIcon.Humidity,
        title = R.string.humidity,
        data = "Low"
    )
}

@Preview
@Composable
private fun SunriseCardPreview() {
    SunriseCard(
        sunriseData = "06:43",
        sunsetData = "18:52"
    )
}

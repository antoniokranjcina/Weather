package com.antoniok.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.antoniok.core.ui.icon.ConditionIcon
import com.antoniok.core.ui.icon.WeatherIcon
import com.antoniok.core.ui.spacing.Spacing
import com.antoniok.weather.feature.home.R

@Composable
fun CurrentWeatherInfo(
    modifier: Modifier = Modifier,
    temp: Int,
    description: String,
    imageCode: Int,
    city: String,
    country: String,
    feelsLikeTemp: Int
) {
    val image = ConditionIcon.findWeatherIcon(imageCode)

    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = city,
                style = MaterialTheme.typography.headlineSmall
            )
            Icon(
                modifier = Modifier.size(Spacing.xl),
                painter = painterResource(id = WeatherIcon.Location),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.size(Spacing.xs))
        Text(
            text = country,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.size(Spacing.l))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$temp°",
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Medium
            )
            Column {
                Text(
                    text = stringResource(id = R.string.feels_like, feelsLikeTemp),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Image(
                modifier = Modifier.size(Spacing.x3l),
                painter = rememberAsyncImagePainter(image),
                contentDescription = null,
            )
        }
    }
}


@Preview
@Composable
private fun WeatherCardPreview() {
    CurrentWeatherInfo(
        temp = 22,
        description = "Rainy",
        imageCode = 0,
        city = "Zagreb",
        country = "Croatia",
        feelsLikeTemp = 22
    )
}

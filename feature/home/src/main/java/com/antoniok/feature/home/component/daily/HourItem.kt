package com.antoniok.feature.home.component.daily

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.antoniok.core.ui.icon.ConditionIcon
import com.antoniok.core.ui.spacing.Spacing
import com.antoniok.feature.home.component.shared.ChanceOfRain

@Composable
fun HourItem(
    modifier: Modifier = Modifier,
    hour: Int,
    imageCode: Int,
    isDay: Boolean,
    temp: Int,
    chanceOfRain: Int
) {
    val image = ConditionIcon.findWeatherIcon(code = imageCode, isDay = isDay)
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$hour:00",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.size(Spacing.xs))
        Image(
            modifier = Modifier.size(Spacing.xl),
            painter = painterResource(id = image),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(Spacing.xs))
        Text(
            text = "$temp°",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.size(Spacing.xs))
        ChanceOfRain(chanceOfRain = chanceOfRain)
    }
}

@Preview
@Composable
private fun HourPreview() {
    HourItem(
        hour = 13,
        imageCode = 0,
        isDay = true,
        temp = 22,
        chanceOfRain = 80
    )
}

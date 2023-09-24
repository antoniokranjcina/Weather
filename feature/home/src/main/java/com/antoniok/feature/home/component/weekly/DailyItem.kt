package com.antoniok.feature.home.component.weekly

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.antoniok.core.ui.icon.WeatherIcon
import com.antoniok.core.ui.spacing.Spacing
import com.antoniok.feature.home.component.shared.ChanceOfRain

@Composable
fun DailyItem(
    modifier: Modifier = Modifier,
    day: String,
    chanceOfRain: Int,
    minConditionImage: String,
    maxConditionImage: String,
    minTemp: Int,
    maxTemp: Int
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = day,
            style = MaterialTheme.typography.labelLarge
        )
        ChanceOfRain(chanceOfRain = chanceOfRain)
        Row {
            Image(
                modifier = Modifier.size(Spacing.xl),
//            painter = rememberAsyncImagePainter(minConditionImage), // TODO will be needed
                painter = painterResource(id = WeatherIcon.Sun),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.size(Spacing.xs))
            Image(
                modifier = Modifier.size(Spacing.xl),
//            painter = rememberAsyncImagePainter(maxConditionImage), // TODO will be needed
                painter = painterResource(id = WeatherIcon.Sun),
                contentDescription = null,
            )
        }
        Row {
            Text(
                text = "$minTemp°",
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.size(Spacing.xs))
            Text(
                text = "$maxTemp°",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview
@Composable
private fun DailyItemPreview() {
    DailyItem(
        day = "Today",
        chanceOfRain = 22,
        minTemp = 12,
        maxTemp = 22,
        minConditionImage = "",
        maxConditionImage = ""
    )
}

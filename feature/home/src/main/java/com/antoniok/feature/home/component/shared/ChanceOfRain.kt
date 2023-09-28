package com.antoniok.feature.home.component.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.antoniok.core.ui.icon.WeatherIcon
import com.antoniok.core.ui.spacing.Spacing

@Composable
fun ChanceOfRain(
    modifier: Modifier = Modifier,
    chanceOfRain: Int
) {
    Row(
        modifier = modifier.padding(start = Spacing.xs, end = Spacing.xs),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        val changeOfRainImage = when (chanceOfRain) {
            in 0..50 -> WeatherIcon.DropOutline
            in 50..100 -> WeatherIcon.DropFill
            else -> WeatherIcon.DropOutline
        }
        Image(
            modifier = Modifier.size(Spacing.l),
            painter = painterResource(id = changeOfRainImage),
            contentDescription = null
        )
        Text(
            text = "$chanceOfRain%",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Normal
        )
    }
}

@Preview
@Composable
fun ChanceOfRainPreview() {
    ChanceOfRain(chanceOfRain = 51)
}

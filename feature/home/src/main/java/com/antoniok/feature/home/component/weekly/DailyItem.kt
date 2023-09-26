package com.antoniok.feature.home.component.weekly

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.antoniok.core.ui.icon.ConditionIcon
import com.antoniok.core.ui.spacing.Spacing
import com.antoniok.feature.home.component.shared.ChanceOfRain

@Composable
fun DailyItem(
    modifier: Modifier = Modifier,
    day: String,
    chanceOfRain: Int,
    minConditionCode: Int,
    maxConditionCode: Int,
    minTemp: Int,
    maxTemp: Int
) {
    val minConditionImage = ConditionIcon.findWeatherIcon(minConditionCode)
    val maxConditionImage = ConditionIcon.findWeatherIcon(maxConditionCode)

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(modifier = Modifier.weight(0.35f)) {
            Text(
                text = day,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Box(modifier = Modifier.weight(0.65f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ChanceOfRain(chanceOfRain = chanceOfRain)
                Row {
                    Image(
                        modifier = Modifier.size(Spacing.xl),
                        painter = painterResource(id = minConditionImage),
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.size(Spacing.xs))
                    Image(
                        modifier = Modifier.size(Spacing.xl),
                        painter = painterResource(id = maxConditionImage),
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
        minConditionCode = 0,
        maxConditionCode = 0
    )
}

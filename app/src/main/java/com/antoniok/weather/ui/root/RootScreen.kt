package com.antoniok.weather.ui.root

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antoniok.weather.navigation.RootNavHost
import com.antoniok.weather.ui.theme.WeatherTheme

@Composable
fun RootScreen(
    modifier: Modifier = Modifier,
    appState: RootScreenState = rememberRootScreenState()
) {
    WeatherTheme {
        Scaffold(modifier = modifier) {
            RootNavHost(
                modifier = Modifier.padding(it),
                navController = appState.navController
            )
        }
    }
}

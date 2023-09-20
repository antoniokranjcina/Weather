package com.antoniok.weather.ui.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberRootScreenState(
    navController: NavHostController = rememberNavController()
): RootScreenState = remember(navController) {
    RootScreenState(navController)
}

class RootScreenState(
    val navController: NavHostController
)

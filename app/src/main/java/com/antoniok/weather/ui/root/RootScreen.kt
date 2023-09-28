package com.antoniok.weather.ui.root

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.antoniok.core.ui.spacing.Spacing
import com.antoniok.weather.navigation.RootNavHost
import com.antoniok.weather.ui.component.Toolbar
import com.antoniok.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun RootScreen(
    modifier: Modifier = Modifier,
    appState: RootScreenState = rememberRootScreenState(),
    viewModel: RootViewModel = getViewModel()
) {
    WeatherTheme {
        val selectedIndex = viewModel.index.collectAsStateWithLifecycle().value
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(modifier = Modifier.size(Spacing.l))
                    viewModel.modalNavigationItems.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                            label = {
                                Text(text = item.title)
                            },
                            selected = selectedIndex == index,
                            onClick = {
                                viewModel.updateIndex(index)
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            badge = {
                                Row {
                                    Image(
                                        painter = painterResource(id = item.conditionIconId),
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.size(Spacing.s))
                                    Text(text = "${item.temperature}°C")
                                }
                            }
                        )
                    }
                }
            }
        ) {
            Scaffold(
                modifier = modifier,
                topBar = {
                    Toolbar(
                        coroutineScope = scope,
                        openDrawerState = {
                            drawerState.open()
                        },
                        historyItems = viewModel.historyItems.collectAsStateWithLifecycle().value,
                        addNewItem = {
                            viewModel.addNewSearchItem(it)
                        }
                    )
                }
            ) {
                RootNavHost(
                    modifier = Modifier.padding(it),
                    navController = appState.navController
                )
            }
        }
    }
}

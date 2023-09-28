package com.antoniok.weather.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.antoniok.core.ui.icon.WeatherIcon
import com.antoniok.core.ui.spacing.Spacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope,
    openDrawerState: suspend () -> Unit,
    historyItems: List<String>,
    addNewItem: (newItem: String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }

    TopAppBar(
        modifier = modifier,
        title = {},
        navigationIcon = {
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        openDrawerState()
                    }
                },
                content = {
                    Icon(
                        imageVector = WeatherIcon.Menu,
                        contentDescription = null
                    )
                }
            )
        },
        actions = {
            IconButton(
                onClick = {
                    isActive = true
                },
                content = {
                    Icon(
                        imageVector = WeatherIcon.Search,
                        contentDescription = null
                    )
                }
            )
        }
    )
    SearchBar(
        modifier = Modifier.alpha(if (isActive) 1f else 0f),
        query = text,
        onQueryChange = { text = it },
        onSearch = {
            addNewItem(it)
            isActive = false
            text = ""
        },
        active = isActive,
        onActiveChange = { isActive = true },
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            Icon(
                imageVector = WeatherIcon.Search,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (isActive) {
                Icon(
                    modifier = Modifier.clickable {
                        if (text.isNotEmpty()) {
                            text = ""
                        } else {
                            isActive = false
                        }
                    },
                    imageVector = WeatherIcon.Close,
                    contentDescription = null
                )
            }
        }
    ) {
        historyItems.forEach {
            Row(
                modifier = Modifier.padding(Spacing.m),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.padding(Spacing.m),
                    painter = painterResource(id = WeatherIcon.History),
                    contentDescription = null
                )
                Text(text = it)
            }
        }
    }
}

@Preview
@Composable
private fun ToolbarPreview() {
    Toolbar(
        coroutineScope = rememberCoroutineScope(),
        openDrawerState = {},
        historyItems = listOf("London", "New York", "Zagreb"),
        addNewItem = {}
    )
}

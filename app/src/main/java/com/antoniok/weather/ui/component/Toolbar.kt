package com.antoniok.weather.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.antoniok.core.model.SearchedLocation
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
    searchedLocations: List<SearchedLocation>,
    searchForLocation: (input: String) -> Unit,
    selectLocation: (location: SearchedLocation) -> Unit
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
    if (isActive) {
        SearchBar(
            modifier = Modifier,
            query = text,
            onQueryChange = { text = it },
            onSearch = {
                searchForLocation(it)
                text = ""
            },
            active = true,
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
            LazyColumn {
                items(searchedLocations) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectLocation(it)
                                isActive = false
                            }
                            .padding(Spacing.l)
                    ) {
                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
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
        searchedLocations = emptyList(),
        searchForLocation = {},
        selectLocation = {}
    )
}

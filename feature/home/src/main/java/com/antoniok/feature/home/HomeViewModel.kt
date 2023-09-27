package com.antoniok.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antoniok.core.data.repository.ModalItemsRepository
import com.antoniok.core.data.repository.WeatherRepository
import com.antoniok.core.model.Weather
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface WeatherUiState {
    object Loading : WeatherUiState
    object Empty : WeatherUiState
    data class Success(val weather: Weather) : WeatherUiState
}

class HomeViewModel(
    private val weatherRepository: WeatherRepository,
    modalItemsRepository: ModalItemsRepository
) : ViewModel() {

    val uiState: StateFlow<WeatherUiState> = combine(
        weatherRepository.weathers,
        modalItemsRepository.index
    ) { weathers, index ->
        Pair(weathers, index)
    }
        .map { (weathers, index) ->
            weathers.getOrNull(index)
        }
        .map { weather ->
            weather?.let { WeatherUiState.Success(it) } ?: WeatherUiState.Empty
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WeatherUiState.Loading,
        )

    var isSyncSuccess by mutableStateOf(false)
        private set

    // TODO move this to Worker
    init {
        viewModelScope.launch {
            isSyncSuccess = weatherRepository.sync(
                city = "New York",
                days = 7
            )
        }
    }

}

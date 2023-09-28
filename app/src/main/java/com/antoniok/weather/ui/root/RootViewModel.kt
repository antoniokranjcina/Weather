package com.antoniok.weather.ui.root

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antoniok.core.data.repository.ModalItemsRepository
import com.antoniok.core.data.repository.WeatherRepository
import com.antoniok.core.ui.icon.ConditionIcon
import com.antoniok.weather.navigation.ModalNavigationItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RootViewModel(
    private val weatherRepository: WeatherRepository,
    private val modalItemsRepository: ModalItemsRepository
) : ViewModel() {

    var modalNavigationItems by mutableStateOf<List<ModalNavigationItem>>(emptyList())
        private set

    private var _index = MutableStateFlow(0)
    val index: StateFlow<Int> = _index

    private var _historyItems = MutableStateFlow<List<String>>(emptyList())
    val historyItems: StateFlow<List<String>> = _historyItems

    fun updateIndex(index: Int) {
        viewModelScope.launch {
            modalItemsRepository.saveIndex(index)
        }
    }

    init {
        collectModalItemIndex()
        collectWeatherItems()
    }

    private fun collectModalItemIndex() {
        viewModelScope.launch {
            modalItemsRepository.index.collectLatest {
                _index.value = it
            }
        }
    }

    private fun collectWeatherItems() {
        viewModelScope.launch {
            weatherRepository.weathers
                .collectLatest { weathers ->
                    modalNavigationItems = weathers.map { weather ->
                        ModalNavigationItem(
                            title = weather.location.name,
                            conditionIconId = ConditionIcon.findWeatherIcon(
                                code = weather.current.condition.code
                            ),
                            temperature = weather.current.tempC.toInt()
                        )
                    }
                    _historyItems.value = weathers.map {
                        it.location.name
                    }
                }
        }
    }

    fun addNewSearchItem(newItem: String) {
        viewModelScope.launch {
        }
    }
}

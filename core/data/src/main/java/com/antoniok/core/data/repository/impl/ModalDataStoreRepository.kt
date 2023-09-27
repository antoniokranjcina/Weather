package com.antoniok.core.data.repository.impl

import com.antoniok.core.data.repository.ModalItemsRepository
import com.antoniok.core.data_source.local.WeatherLocalDataSource
import kotlinx.coroutines.flow.Flow

internal class ModalDataStoreRepository(
    private val localDataSource: WeatherLocalDataSource
) : ModalItemsRepository {
    override suspend fun saveIndex(index: Int) {
        localDataSource.saveIndex(index)
    }

    override val index: Flow<Int>
        get() = localDataSource.index
}

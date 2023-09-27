package com.antoniok.core.data.repository

import kotlinx.coroutines.flow.Flow

interface ModalItemsRepository {

    /**
     * Saves the specified [index] to the local data store.
     *
     * @param index The integer index to be saved.
     */
    suspend fun saveIndex(index: Int)

    /**
     * A flow of integer values representing an index.
     * Observers can collect values from this flow to stay updated on the current index.
     */
    val index: Flow<Int>

}

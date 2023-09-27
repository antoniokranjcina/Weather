package com.antoniok.core.data_source.local.preferences

import kotlinx.coroutines.flow.Flow

/**
 * This is an internal interface representing a local data store. It provides methods and properties
 * for working with data locally.
 */
internal interface LocalDataStore {

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

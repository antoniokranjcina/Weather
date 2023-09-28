package com.antoniok.core.data.repository

import com.antoniok.core.model.SearchedLocation

/**
 * This interface defines a contract for retrieving a list of searched locations based on the
 * provided input.
 */
interface SearchCityRepository {

    /**
     * Retrieves a list of searched locations based on the provided [location].
     *
     * @param location The location used for searching cities or locations.
     * @return A list of [SearchedLocation] objects representing the search results.
     */
    suspend fun getCities(location: String): List<SearchedLocation>
}

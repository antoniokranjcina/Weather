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

    /**
     * Suspends the execution to save the selected city and then triggers the provided [update]
     * function once the city has been successfully saved.
     *
     * @param city The name of the city to be saved.
     * @param update A suspend lambda that will be called after saving the city.
     *               You can use this lambda to perform any additional tasks that should
     *               occur after the city has been successfully saved.
     */
    suspend fun saveSelectedCity(city: String, update: suspend () -> Unit)
}

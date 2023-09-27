package com.antoniok.core.data

/**
 * The `Sync` interface defines a contract for performing data synchronization operations.
 * Implementations of this interface are responsible for synchronizing weather data
 * for a specified city and forecast period in days.
 */
interface Sync {

    /**
     * Synchronizes weather data for the specified [city] and forecast period in [days].
     *
     * @param city The name of the city for which data will be requested if there is no data in db.
     * @param days The number of days for which weather forecast data should be synchronized.
     * @return `true` if the synchronization was successful, `false` otherwise.
     */
    suspend fun sync(city: String, days: Int): Boolean

}

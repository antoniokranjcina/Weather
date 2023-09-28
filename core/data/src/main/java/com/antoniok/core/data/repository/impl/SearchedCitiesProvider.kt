package com.antoniok.core.data.repository.impl

import com.antoniok.core.data.repository.SearchCityRepository
import com.antoniok.core.model.SearchedLocation
import com.antoniok.weather.data_source.remote.WeatherNetworkDataSource
import com.antoniok.weather.data_source.remote.model.location.SearchedLocationDto
import com.antoniok.weather.data_source.remote.model.location.asExternalModel
import com.antoniok.weather.data_source.remote.resource.NetworkResource

internal class SearchedCitiesProvider(
    private val networkDataSource: WeatherNetworkDataSource
) : SearchCityRepository {
    override suspend fun getCities(location: String): List<SearchedLocation> =
        when (val response = networkDataSource.getLocations(location)) {
            is NetworkResource.Success -> {
                response.data.map(SearchedLocationDto::asExternalModel)
            }

            is NetworkResource.Error -> {
                emptyList()
            }
        }
}

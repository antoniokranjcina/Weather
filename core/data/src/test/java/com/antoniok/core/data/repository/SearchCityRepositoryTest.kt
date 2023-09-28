package com.antoniok.core.data.repository

import com.antoniok.core.data.repository.impl.SearchedCitiesProvider
import com.antoniok.core.model.SearchedLocation
import com.antoniok.weather.data_source.remote.WeatherNetworkDataSource
import com.antoniok.weather.data_source.remote.model.location.SearchedLocationDto
import com.antoniok.weather.data_source.remote.model.location.asExternalModel
import com.antoniok.weather.data_source.remote.resource.NetworkResource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SearchedCitiesProviderTest {

    @Mock
    private lateinit var mockNetworkDataSource: WeatherNetworkDataSource
    private lateinit var searchedCitiesProvider: SearchCityRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        searchedCitiesProvider = SearchedCitiesProvider(mockNetworkDataSource)
    }

    @Test
    fun `GIVEN valid location WHEN getCities is called THEN return list of SearchedLocation`() =
        runBlocking {
            val location = "New York"

            val successResponse = NetworkResource.Success(expectedLocations)

            `when`(mockNetworkDataSource.getLocations(location)).thenReturn(successResponse)
            val result = searchedCitiesProvider.getCities(location)

            assertEquals(
                expectedLocations.map(SearchedLocationDto::asExternalModel),
                result
            )
        }

    @Test
    fun `GIVEN empty location WHEN getCities is called THEN return an empty list`() =
        runBlocking {
            val location = ""
            val successResponse = NetworkResource.Success(emptyList<SearchedLocationDto>())

            // WHEN
            `when`(mockNetworkDataSource.getLocations(location)).thenReturn(successResponse)
            val result = searchedCitiesProvider.getCities(location)

            assertEquals(emptyList<SearchedLocation>(), result)
        }

    @Test
    fun `GIVEN invalid location WHEN getCities is called THEN return an empty list`() =
        runBlocking {
            val location = "Invalid Location"
            val errorResponse =
                NetworkResource.Error<List<SearchedLocationDto>>(Exception("Invalid location"))

            `when`(mockNetworkDataSource.getLocations(location)).thenReturn(errorResponse)
            val result = searchedCitiesProvider.getCities(location)

            assertEquals(emptyList<SearchedLocation>(), result)
        }

    companion object {
        private val searchedLocationDto1 = SearchedLocationDto(
            id = 1,
            name = "Zagreb",
            region = "Grad Zagreb",
            country = "Croatia",
            lat = 12.2,
            lon = 123.1,
            url = "www.croatia.hr"
        )

        private val searchedLocationDto2 = SearchedLocationDto(
            id = 1,
            name = "Zagreb",
            region = "Grad Zagreb",
            country = "Croatia",
            lat = 12.2,
            lon = 123.1,
            url = "www.croatia.hr"
        )

        val expectedLocations = listOf(searchedLocationDto1, searchedLocationDto2)
    }
}

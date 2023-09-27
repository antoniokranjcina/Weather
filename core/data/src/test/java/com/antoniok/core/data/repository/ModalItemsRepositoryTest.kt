package com.antoniok.core.data.repository

import com.antoniok.core.data.repository.impl.ModalDataStoreRepository
import com.antoniok.core.data_source.local.WeatherLocalDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class ModalItemsRepositoryTest {

    @Mock
    private lateinit var localDataSource: WeatherLocalDataSource

    private lateinit var repository: ModalItemsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = ModalDataStoreRepository(localDataSource)
    }

    @Test
    fun `GIVEN saveIndex is called WHEN an index is provided THEN it should be saved`() =
        runBlocking {
            localDataSource.saveIndex(42)
            verify(localDataSource).saveIndex(42)
        }

    @Test
    fun `GIVEN index flow is requested WHEN data is available THEN it should return the flow`() =
        runBlocking {
            `when`(repository.index).thenReturn(flowOf(1))
            val result = repository.index.first()
            assertEquals(result, 1)
        }

}

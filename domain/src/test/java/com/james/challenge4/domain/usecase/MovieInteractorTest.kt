package com.james.challenge4.domain.usecase

import com.james.challenge4.DataDummy
import com.james.challenge4.domain.model.ApiResponse
import com.james.challenge4.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class MovieInteractorTest {

    private val movieRepository = mock<IMovieRepository>()
    private val movieInteractor = MovieInteractor(movieRepository)

    @Test
    fun getAllMovie() = runTest {
        val expected = DataDummy.generateDummyListMovies()

        whenever(movieRepository.getAllMovie()).thenReturn(flowOf(ApiResponse.Success(DataDummy.generateDummyListMovies())))
        movieInteractor.getAllMovie().collect{result ->
            assertNotNull((result as ApiResponse.Success).data.size)
            assertEquals(expected.size, result.data.size)
        }
    }
}
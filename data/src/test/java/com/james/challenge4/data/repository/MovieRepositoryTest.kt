package com.james.challenge4.data.repository

import com.james.challenge4.data.source.remote.movie.DataDummy
import com.james.challenge4.data.source.remote.movie.RemoteDataSourceMovie
import com.james.challenge4.data.utils.DataMapper
import com.james.challenge4.domain.model.ApiResponse
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class MovieRepositoryTest {

    private val remoteDataSourceMovie = mock<RemoteDataSourceMovie>()
    private val movieRepository = MovieRepository(remoteDataSourceMovie)

    @Test
    fun getAllMovie() = runTest {
        val listMoviesResponse = DataDummy.generateDummyMovieResponse().results
        val listMoviesEntities = listMoviesResponse?.let {
            DataMapper.mapResponsesToEntities(it)
        }

        whenever(remoteDataSourceMovie.getAllMovie()).thenReturn(
            listMoviesEntities?.let {
                flowOf(ApiResponse.Success(it))
            }
        )
        val expected = DataDummy.generateDummyMovieResponse()
        movieRepository.getAllMovie().collect { result ->
            assertNotNull((result as ApiResponse.Success).data.size)
            assertEquals(expected.totalResults, (result as ApiResponse.Success).data.size)
        }
    }
}
package com.james.challenge4.data.source.remote.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.james.challenge4.data.MainDispatcherRule
import com.james.challenge4.data.source.remote.movie.network.ApiService
import com.james.challenge4.domain.model.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test

@ExperimentalCoroutinesApi
class RemoteDataSourceMovieTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var apiService: ApiService
    private lateinit var remoteDataSourceMovie: RemoteDataSourceMovie

    @Before
    fun setUp() {
        apiService = FakeApiService()
        remoteDataSourceMovie = RemoteDataSourceMovie(apiService)
    }

    @Test
    fun getAllMovieNotNull() = runTest {
        val expectedMovies = DataDummy.generateDummyMovieResponse()
        val actualMovies = remoteDataSourceMovie.getAllMovie()

        actualMovies.collect{ result ->
            assertNotNull((result as ApiResponse.Success).data.size)
            assertEquals(expectedMovies.totalResults, (result as ApiResponse.Success).data.size)
        }
    }
}
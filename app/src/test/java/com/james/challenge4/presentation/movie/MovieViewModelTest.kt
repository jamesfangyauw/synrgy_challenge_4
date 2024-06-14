package com.james.challenge4.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.james.challenge4.DataDummy
import com.james.challenge4.MainDispatcherRule
import com.james.challenge4.domain.model.ApiResponse
import com.james.challenge4.domain.usecase.MovieUseCase
import com.james.challenge4.observeForTesting
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var movieUseCase: MovieUseCase

    private lateinit var viewmodel: MovieViewModel

    @Before
    fun setUp() {
        movieUseCase = FakeMovieUseCase()
        viewmodel = MovieViewModel(movieUseCase)
    }

    @Test
    fun getAllMovie() = runTest {
        val expected = DataDummy.generateDummyListMovies()
        val actual = viewmodel.movie
        actual.observeForTesting {
            assertNotNull(actual)
            assertEquals(expected, (actual.value as ApiResponse.Success).data)
        }
    }
}
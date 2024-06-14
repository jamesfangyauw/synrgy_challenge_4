package com.james.challenge4.presentation.movie

import com.james.challenge4.DataDummy
import com.james.challenge4.domain.model.ApiResponse
import com.james.challenge4.domain.model.Movie
import com.james.challenge4.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeMovieUseCase : MovieUseCase {
    override suspend fun getAllMovie(): Flow<ApiResponse<List<Movie>>> {
        return flowOf(ApiResponse.Success(DataDummy.generateDummyListMovies()))
    }
}
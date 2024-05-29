package com.james.challenge4.domain.usecase

import com.james.challenge4.domain.model.ApiResponse
import com.james.challenge4.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    suspend fun getAllMovie() : Flow<ApiResponse<List<Movie>>>

}
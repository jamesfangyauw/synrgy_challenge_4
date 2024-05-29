package com.james.challenge4.domain.repository


import com.james.challenge4.domain.model.ApiResponse
import com.james.challenge4.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getAllMovie() : Flow<ApiResponse<List<Movie>>>

}
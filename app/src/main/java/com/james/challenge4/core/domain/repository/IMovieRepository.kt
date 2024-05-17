package com.james.challenge4.core.domain.repository


import com.james.challenge4.core.data.source.remote.movie.network.ApiResponse
import com.james.challenge4.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getAllMovie() : Flow<ApiResponse<List<Movie>>>

}
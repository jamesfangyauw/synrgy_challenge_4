package com.james.challenge4.core.domain.usecase

import com.james.challenge4.core.data.source.remote.movie.network.ApiResponse
import com.james.challenge4.core.domain.model.Movie
import com.james.challenge4.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieInteractor @Inject constructor (private val movieRepository: IMovieRepository) :
    MovieUseCase {
    override suspend fun getAllMovie(): Flow<ApiResponse<List<Movie>>> {
        return movieRepository.getAllMovie()
    }

}
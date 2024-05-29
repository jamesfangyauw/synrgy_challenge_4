package com.james.challenge4.domain.usecase

import com.james.challenge4.domain.model.ApiResponse
import com.james.challenge4.domain.model.Movie
import com.james.challenge4.domain.repository.IMovieRepository
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
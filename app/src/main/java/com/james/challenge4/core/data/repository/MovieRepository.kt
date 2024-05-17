package com.james.challenge4.core.data.repository

import com.james.challenge4.core.data.source.remote.movie.RemoteDataSourceMovie
import com.james.challenge4.core.data.source.remote.movie.network.ApiResponse
import com.james.challenge4.core.domain.model.Movie
import com.james.challenge4.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
  private val remoteDataSourceMovie: RemoteDataSourceMovie
) : IMovieRepository {
    override suspend fun getAllMovie(): Flow<ApiResponse<List<Movie>>> = remoteDataSourceMovie.getAllMovie()

}
package com.james.challenge4.data.source.remote.movie.network

import com.james.challenge4.data.source.remote.movie.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("discover/movie")
    suspend fun getAllMovie() : ListMovieResponse
}
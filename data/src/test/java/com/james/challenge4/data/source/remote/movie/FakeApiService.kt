package com.james.challenge4.data.source.remote.movie

import com.james.challenge4.data.source.remote.movie.network.ApiService
import com.james.challenge4.data.source.remote.movie.response.ListMovieResponse
import com.james.challenge4.data.source.remote.movie.response.MovieResponse

class FakeApiService : ApiService {
    override suspend fun getAllMovie(): ListMovieResponse {
        return DataDummy.generateDummyMovieResponse()
    }
}

object DataDummy {

    fun generateDummyMovieResponse () : ListMovieResponse {
        val movieList = mutableListOf<MovieResponse>()
        for (i in 0..10){
            val movie = MovieResponse(
                overview = "This is the best of movie, he get $i million viewer for 1 day",
                originalTitle = "Movie $i"
            )
            movieList.add(movie)
        }
        return ListMovieResponse(results = movieList, totalResults = movieList.size)

    }


}
package com.james.challenge4

import com.james.challenge4.domain.model.Movie


object DataDummy {

    fun generateDummyListMovies(): List<Movie> {
        val movieList = mutableListOf<Movie>()
        for (i in 0..10) {
            val movie = Movie(
                overview = "This is the best of movie, he get $i million viewer for 1 day",
                title = "Movie $i",
                idMovie = i,
                isFavorite = false,
                posterPath = "/$i"
            )
            movieList.add(movie)
        }
        return movieList

    }
}
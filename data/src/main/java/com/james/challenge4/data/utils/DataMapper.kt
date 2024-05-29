package com.james.challenge4.data.utils

object DataMapper {
    fun mapResponsesToEntities(input: List<com.james.challenge4.data.source.remote.movie.response.MovieResponse>): List<com.james.challenge4.domain.model.Movie> {
        val movieList = ArrayList<com.james.challenge4.domain.model.Movie>()
        input.map {
            val movie = com.james.challenge4.domain.model.Movie(
                idMovie = it.id ?: 0,
                title = it.title ?: "Not Found",
                overview = it.overview ?: "Not Found",
                posterPath = it.posterPath ?: "not found",
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<com.james.challenge4.data.source.local.note.NoteEntity>): List<com.james.challenge4.domain.model.Note> =
        input.map {
            com.james.challenge4.domain.model.Note(
                id = it.id,
                title = it.title,
                content = it.content
            )
        }
}
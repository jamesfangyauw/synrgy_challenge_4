package com.james.challenge4.core.utils

import com.james.challenge4.core.data.source.local.note.NoteEntity
import com.james.challenge4.core.data.source.remote.movie.response.MovieResponse
import com.james.challenge4.core.domain.model.Movie
import com.james.challenge4.core.domain.model.Note

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<Movie> {
        val movieList = ArrayList<Movie>()
        input.map {
            val movie = Movie(
                idMovie = it.id ?:0,
                title = it.title ?: "Not Found",
                overview = it.overview ?: "Not Found",
                posterPath = it.posterPath ?:"not found",
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<NoteEntity>): List<Note> =
        input.map {
            Note(
                id = it.id,
                title = it.title,
                content = it.content
            )
        }
}
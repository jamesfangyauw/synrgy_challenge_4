package com.james.challenge4.domain.model



data class Movie (
    val idMovie : Int,
    val title : String,
    val overview : String,
    val posterPath : String,
    val isFavorite : Boolean
)

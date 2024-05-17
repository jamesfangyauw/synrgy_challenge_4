package com.james.challenge4.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    val idMovie : Int,
    val title : String,
    val overview : String,
    val posterPath : String,
    val isFavorite : Boolean
) : Parcelable
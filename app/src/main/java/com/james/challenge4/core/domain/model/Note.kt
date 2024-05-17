package com.james.challenge4.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note (
    val id : Int?,
    val title : String,
    val content : String
) : Parcelable
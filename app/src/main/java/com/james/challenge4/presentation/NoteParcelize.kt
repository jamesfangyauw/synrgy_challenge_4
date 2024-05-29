package com.james.challenge4.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteParcelize (
    val id : Int?,
    val title : String,
    val content : String
) : Parcelable
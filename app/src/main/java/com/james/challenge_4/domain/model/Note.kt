package com.james.challenge_4.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Note (
    val id : Int?,
    val title : String,
    val content : String
) : Parcelable
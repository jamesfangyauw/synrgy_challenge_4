package com.james.challenge4.core.data.source.remote.movie.network

sealed class ApiResponse<out R> {
    data class Success<T> (val data : T) : ApiResponse<T>()
    data class Error(val errorMessage : String) : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}
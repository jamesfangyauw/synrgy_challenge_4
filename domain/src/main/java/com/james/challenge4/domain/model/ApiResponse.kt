package com.james.challenge4.domain.model

sealed class ApiResponse<out R> {
    data class Success<T> (val data : T) : ApiResponse<T>()
    data class Error(val errorMessage : String) : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}
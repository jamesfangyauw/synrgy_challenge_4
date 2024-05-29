package com.james.challenge4.presentation.movie

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.james.challenge4.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor (val movieUseCase: MovieUseCase) : ViewModel() {

    val movie = liveData {
        try {
            val movies = movieUseCase.getAllMovie()
            emit(movies.first())
        } catch (e: Exception) {
            // Handle the exception appropriately
            Log.e("MovieViewModel", "Error fetching movies", e)
        }
    }


}
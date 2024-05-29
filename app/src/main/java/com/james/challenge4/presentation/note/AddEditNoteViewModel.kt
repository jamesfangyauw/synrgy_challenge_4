package com.james.challenge4.presentation.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.james.challenge4.domain.model.Note
import com.james.challenge4.domain.usecase.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(private val noteInteractor: NoteUseCase) : ViewModel() {

    private var _isSuccess = MutableLiveData<Boolean>()
    val isSuccess : LiveData<Boolean> = _isSuccess

    private var _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    fun addNote(note: Note){
        viewModelScope.launch {
            try {
                noteInteractor.addEditNote(note)
                _isSuccess.value = true
            } catch (error: Throwable){
                _error.value = error.toString()
            }

        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch {
            try {
                noteInteractor.updateNote(note)
                _isSuccess.value = true
            } catch (error: Throwable){
                _error.value = error.toString()
            }
        }
    }
}
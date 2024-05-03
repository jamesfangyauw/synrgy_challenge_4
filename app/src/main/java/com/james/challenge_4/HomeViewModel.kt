package com.james.challenge_4

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.james.challenge_4.core.data.source.AuthRepository
import com.james.challenge_4.core.data.source.NoteRepository
import com.james.challenge_4.domain.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
if you are lazy to make each livedata val, so you can set the return value of function
with live data. Of course function from repository/dao should return live data to (alternative
you can use Flow or RX java to in repository but in view model should using live data because live data can
be observed by view (activity or fragment). Then in view you can observe the function which is return live data type
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val noteRepository: NoteRepository, private val authRepository: AuthRepository) : ViewModel() {

    fun getAllNote() : LiveData<List<Note>>?{
        Log.d("recall", "im recall")
        return noteRepository.getAllNote()?.asLiveData()

    }
    //alternative syntax for getAllNote
    //val getAllNote =noteRepository.getAllNote()?.asLiveData()

    fun deleteNote(note: Note){
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }

    fun logOut(){
        authRepository.clearToken()
    }
}
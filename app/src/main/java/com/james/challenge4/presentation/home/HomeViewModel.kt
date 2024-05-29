package com.james.challenge4.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.james.challenge4.domain.model.Note
import com.james.challenge4.domain.usecase.AuthUseCase
import com.james.challenge4.domain.usecase.NoteUseCase
import com.james.challenge4.presentation.NoteParcelize
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
if you are lazy to make each livedata val, so you can set the return value of function
with live data. Of course function from repository/dao should return live data to (alternative
you can use Flow or RX java to in repository but in view model should using live data because live data can
be observed by view (activity or fragment). Then in view you can observe the function which is return live data type
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val noteInteractor: NoteUseCase, private val authInteractor: AuthUseCase) : ViewModel() {

    fun getAllNote() : LiveData<List<NoteParcelize>>?{
        Log.d("recall", "im recall")
        return noteInteractor.getAllNote()?.map { it.map { NoteParcelize(it.id, it.title, it.content) } }?.asLiveData()

    }
    //alternative syntax for getAllNote
    //val getAllNote =noteRepository.getAllNote()?.asLiveData()

    fun deleteNote(note: Note){
        viewModelScope.launch {
            noteInteractor.deleteNote(note)
        }
    }

    fun logOut(){
        authInteractor.clearToken()
    }

//    fun getName() : Deferred<String?> {
//        return viewModelScope.async {
//            return@async authRepository.loadName()
//        }
//    }

    suspend fun getName(): Flow<String?> {
        return flow {
            val data = authInteractor.loadName()
            emit(data)
        }
    }
}
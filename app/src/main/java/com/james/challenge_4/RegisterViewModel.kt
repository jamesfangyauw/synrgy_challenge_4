package com.james.challenge_4

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.james.challenge_4.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor (private val authUseCase: AuthUseCase) : ViewModel() {

    private var _isSuccess = MutableLiveData<Boolean>()
    var isSuccess : LiveData <Boolean> = _isSuccess

    fun register(email : String, password : String){
        viewModelScope.launch { authUseCase.register(email,password)
            _isSuccess.value = true
        }
        Log.d("test321", "testing testing")

    }
}
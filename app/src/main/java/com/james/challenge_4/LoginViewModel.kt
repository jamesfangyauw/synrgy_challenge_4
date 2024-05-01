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
class LoginViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel(){
    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    fun login(email : String, password : String){
        viewModelScope.launch {
            try {
                val token = authUseCase.login(email,password)
                authUseCase.saveToken(token)
                _success.value = true
            } catch (throwable:Throwable){
                _success.value = false
            }


        }
        Log.d("test321", "testing testing")
    }
}
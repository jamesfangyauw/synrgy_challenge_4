package com.james.challenge4.presentation.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.james.challenge4.core.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor (private val authUseCase: AuthUseCase) : ViewModel() {

    private var _status = MutableLiveData<Boolean>()
    val status = _status

    private var _isloading = MutableLiveData<Boolean>()
    val isloading = _isloading
    fun checklogin(){
        _isloading.value = true
        _status.value = !authUseCase.loadToken().isNullOrEmpty()
        _isloading.value = false
    }
}
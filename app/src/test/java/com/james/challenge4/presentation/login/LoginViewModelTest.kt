package com.james.challenge4.presentation.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.james.challenge4.MainDispatcherRule
import com.james.challenge4.domain.usecase.AuthUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LoginViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val authUseCase = mock<AuthUseCase>()

    private val viewModel = LoginViewModel(authUseCase)

    private val successObserver = mock<Observer<Boolean>>()

    private val successCaptor = argumentCaptor<Boolean>()

    @Test
    fun loginSuccess() = runTest {
        val email = "jamesfang@gmail.com"
        val password = "secret"
        val token = "initoken123"

        val successLiveData = viewModel.success

        successLiveData.observeForever(successObserver)

        whenever(authUseCase.login(email, password)).thenReturn(token)

        viewModel.login(email, password)

        // verify
        verify(successObserver).onChanged(successCaptor.capture())
        assertEquals(successCaptor.allValues, listOf(true))
    }

    @Test
    fun loginFailed() = runTest {
        val email = "jamesfang@gmail.com"
        val password = "secret"
        val token = "initoken123"

        val successLiveData = viewModel.success

        successLiveData.observeForever(successObserver)

        whenever(authUseCase.login(email, password)).thenThrow()

        viewModel.login(email, password)

        // verify
        verify(successObserver).onChanged(successCaptor.capture())
        assertEquals(successCaptor.allValues, listOf(false))

    }
}


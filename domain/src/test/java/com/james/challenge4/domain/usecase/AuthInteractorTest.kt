package com.james.challenge4.domain.usecase

import com.james.challenge4.domain.repository.IAuthRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class AuthInteractorTest {

    private val authRepository = mock<IAuthRepository>()
    private val authInteractor = AuthInteractor(authRepository)

    @Test
    fun loginSuccess() = runTest {
        val email = "jamesfang@gmail.com"
        val password = "secret"

        whenever(authRepository.login(email,password)).thenReturn("initoken123")
        val expected = "initoken123"
        val actual = authInteractor.login(email, password)
        assertEquals(expected, actual)
    }
}
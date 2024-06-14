package com.james.challenge4.data.repository

import com.james.challenge4.data.source.local.auth.LocalDataSourceAuth
import com.james.challenge4.data.source.remote.auth.RemoteDataSourceAuth
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class AuthRepositoryTest {

    private val remoteDataSourceAuth = mock<RemoteDataSourceAuth>()
    private val localDataSourceAuth = mock<LocalDataSourceAuth>()
    private val authRepository = AuthRepository(remoteDataSourceAuth, localDataSourceAuth)

    @Test
    fun login() = runTest {
        val email = "jamesfang@gmail.com"
        val password = "secret"

        whenever(remoteDataSourceAuth.login(email,password)).thenReturn("initoken123")
        val expected = "initoken123"
        val actual = authRepository.login(email, password)
        assertEquals(expected, actual)

    }
}
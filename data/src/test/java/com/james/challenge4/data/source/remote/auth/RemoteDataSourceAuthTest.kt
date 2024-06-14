package com.james.challenge4.data.source.remote.auth

import android.content.SharedPreferences
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever


class RemoteDataSourceAuthTest {

    companion object {
        const val KEY_EMAIL = "email@gmail.com"
        const val KEY_PASSWORD = "rahasia"
    }

    private val sharedPreferences = mock<SharedPreferences>()
    private val remoteDataSourceAuth = RemoteDataSourceAuth(sharedPreferences)


    @Test
    fun `login success`() = runTest {
        val username = "jamesfang@gmail.com"
        val password = "secret"

        whenever(sharedPreferences.getString(KEY_EMAIL, null)).thenReturn("jamesfang@gmail.com")
        whenever(sharedPreferences.getString(KEY_PASSWORD, null)).thenReturn("secret")

        val expected = "initoken123"
        val actual = remoteDataSourceAuth.login(username, password)

        assertEquals(expected, actual)
    }

    @Test
    fun loginFailed() = runTest {
        val username = "jamesfang@gmail.com"
        val password = "password"

        whenever(sharedPreferences.getString(KEY_EMAIL, null)).thenReturn("jamesfang@gmail.com")
        whenever(sharedPreferences.getString(KEY_PASSWORD, null)).thenReturn("secret")

        val expected = "email atau password anda salah"

        val actual = try {
            remoteDataSourceAuth.login(username, password)
            Assert.fail("Expected UnsupportedOperationException to be thrown")
        } catch (e: UnsupportedOperationException) {
            e.message
        }

        assertEquals(expected,actual)
    }
}
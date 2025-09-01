package com.example.coursesapp.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursesapp.auth.AuthState
import com.example.data.datastore.AuthDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "GlobalAuthViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authDataStore: AuthDataStore
) : ViewModel() {

    private val _authState = mutableStateOf<AuthState>(AuthState.Loading)
    val authState: State<AuthState> = _authState

    private val _showSplash = mutableStateOf(true)
    val showSplash: State<Boolean> = _showSplash

    init {
        Log.d(TAG, "MainViewModel initialized")
        checkAuth()
    }

    /**
     * Простая проверка авторизации
     */
    private fun checkAuth() {
        viewModelScope.launch {
            delay(1000)

            val hasSession = authDataStore.isUserLoggedIn()

            _authState.value = if (hasSession) {
                AuthState.Authenticated("user")
            } else {
                AuthState.Unauthenticated
            }

            _showSplash.value = false

            observeAuthStateChanges()
        }
    }

    private fun observeAuthStateChanges() {
        viewModelScope.launch {
            authDataStore.isLoggedInFlow.collect { isLoggedIn ->
                _authState.value = if (isLoggedIn) {
                    AuthState.Authenticated("user")
                } else {
                    AuthState.Unauthenticated
                }
            }
        }
    }

    val isAuthenticated: Boolean
        get() = authState.value is AuthState.Authenticated
}
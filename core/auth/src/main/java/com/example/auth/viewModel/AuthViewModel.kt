package com.example.auth.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.auth.state.AuthState

/**
 * ViewModel для управления состоянием авторизации
 * Находится в app модуле и координирует работу feature модулей
 */

private const val AUTH_VIEW_MODEL = "AuthViewModel"

class AuthViewModel : ViewModel() {
    private val _authState = mutableStateOf<AuthState>(AuthState.Loading)
    val authState: State<AuthState> = _authState

    init {
        Log.d(AUTH_VIEW_MODEL, "AuthViewModel initialized")
        checkSavedSession()
    }

    private fun checkSavedSession() {
        Log.d(AUTH_VIEW_MODEL, "Checking saved session...")
        // Симуляция проверки токена
        _authState.value = AuthState.Unauthenticated
        Log.d(AUTH_VIEW_MODEL, "No saved session - user unauthenticated")
    }

    fun signIn(userId: String = "demo_user") {
        Log.d(AUTH_VIEW_MODEL, "User signing in with userId: $userId")
        _authState.value = AuthState.Authenticated(userId)
    }

    fun signOut() {
        Log.d(AUTH_VIEW_MODEL, "User signing out")
        _authState.value = AuthState.Unauthenticated
    }

    val isAuthenticated: Boolean
        get() = authState.value is AuthState.Authenticated
}
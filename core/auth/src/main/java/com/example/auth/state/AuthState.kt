package com.example.auth.state

private const val AUTH_STATE_TAG = "AuthState"

/**
 * Состояния авторизации в многомодульном приложении
 * Управляет переключением между feature модулями
 */
sealed class AuthState {
    object Loading : AuthState()
    object Unauthenticated : AuthState()
    data class Authenticated(val userId: String = "") : AuthState()
}




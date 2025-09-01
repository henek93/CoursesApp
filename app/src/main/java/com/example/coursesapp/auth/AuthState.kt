package com.example.coursesapp.auth

sealed class AuthState {
    object Loading : AuthState()
    object Unauthenticated : AuthState()
    data class Authenticated(val userId: String) : AuthState()
}
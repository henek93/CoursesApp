package com.example.authorization.presentation

data class AuthorizationScreenState(
    val email: String,
    val password: String,
    val emailError: String?,
    val passwordError: String?,
    val isLoading: Boolean
)
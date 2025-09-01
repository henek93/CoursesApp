package com.example.authorization.domain

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun getAuthFlow(): Flow<Boolean>

    suspend fun signIn()

    suspend fun signOut()

    suspend fun checkSavedSession(): Boolean
}
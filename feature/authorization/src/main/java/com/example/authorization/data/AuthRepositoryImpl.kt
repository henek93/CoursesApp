package com.example.authorization.data

import com.example.authorization.domain.AuthRepository
import com.example.data.datastore.AuthDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataStore: AuthDataStore
) : AuthRepository {

    override fun getAuthFlow(): Flow<Boolean> = authDataStore.isLoggedInFlow

    override suspend fun signIn() {
        authDataStore.saveLoggedIn()
    }

    override suspend fun signOut() {
        authDataStore.saveLogOut()
    }

    override suspend fun checkSavedSession(): Boolean = authDataStore.isUserLoggedIn()


}
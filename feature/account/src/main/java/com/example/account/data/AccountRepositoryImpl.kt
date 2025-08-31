package com.example.account.data

import com.example.account.domain.AccountRepository
import com.example.data.datastore.AuthDataStore
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val authDataStore: AuthDataStore
) : AccountRepository {


    override suspend fun signOut() {
        authDataStore.saveLogOut()
    }


}
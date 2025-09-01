package com.example.account.domain

interface AccountRepository {

    suspend fun signOut()

}
package com.example.account.data

import com.example.account.domain.AccountRepository
import com.example.data.datastore.AuthDataStore
import com.example.data.local.db.CourseDao
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val authDataStore: AuthDataStore,
    private val courseDao: CourseDao
) : AccountRepository {


    override suspend fun signOut() {
        courseDao.clearAllCourses()
        authDataStore.saveLogOut()
    }


}
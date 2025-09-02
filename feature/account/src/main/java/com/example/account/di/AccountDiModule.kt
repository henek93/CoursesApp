package com.example.account.di

import com.example.account.data.AccountRepositoryImpl
import com.example.account.domain.AccountRepository
import com.example.data.datastore.AuthDataStore
import com.example.data.local.db.CourseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AccountDiModule {

    @Singleton
    @Provides
    fun provideAccountRepository(
        authDataStore: AuthDataStore,
        courseDao: CourseDao
    ): AccountRepository = AccountRepositoryImpl(authDataStore, courseDao)
}
package com.example.authorization.di

import com.example.authorization.data.AuthRepositoryImpl
import com.example.authorization.domain.AuthRepository
import com.example.data.datastore.AuthDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthorizationModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authDataStore: AuthDataStore
    ): AuthRepository {
        return AuthRepositoryImpl(authDataStore)
    }
}
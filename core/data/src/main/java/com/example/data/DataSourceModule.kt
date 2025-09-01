package com.example.data

import android.content.Context
import com.example.data.datastore.AuthDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideAuthDataStore(
        @ApplicationContext context: Context
    ): AuthDataStore {
        return AuthDataStore(context)
    }

}
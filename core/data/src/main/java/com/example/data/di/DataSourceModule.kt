package com.example.data.di

import android.content.Context
import com.example.data.datastore.AuthDataStore
import com.example.data.local.db.CourseDao
import com.example.data.local.db.CourseDatabase
import com.example.data.network.ApiFactory
import com.example.data.network.ApiService
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

    @Singleton
    @Provides
    fun provideApiService(): ApiService = ApiFactory.api

    @Singleton
    @Provides
    fun provideCourseDao(
        @ApplicationContext context: Context
    ): CourseDao = CourseDatabase.getInstance(context).dao()

}
package com.example.home.di

import com.example.data.network.ApiService
import com.example.home.data.HomeRepositoryImpl
import com.example.home.domain.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeDiModule {

    @Singleton
    @Provides
    fun provideHomeRepository(
        apiService: ApiService
    ): HomeRepository = HomeRepositoryImpl(apiService)

}
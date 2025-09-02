package com.example.favourite.di

import com.example.data.local.db.CourseDao
import com.example.favourite.data.FavouriteRepositoryImpl
import com.example.favourite.domain.FavouriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavouriteDiModule {

    @Singleton
    @Provides
    fun provideFavouriteRepository(
        courseDao: CourseDao
    ): FavouriteRepository = FavouriteRepositoryImpl(courseDao)
}
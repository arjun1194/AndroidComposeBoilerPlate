package com.example.androidcomposeboilerplate.di

import com.example.androidcomposeboilerplate.data.repository.UnsplashRepositoryImpl
import com.example.androidcomposeboilerplate.domain.repository.UnsplashRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindItemRepository(
        itemRepositoryImpl: UnsplashRepositoryImpl
    ): UnsplashRepository
}

package com.techskaud.sampleapp.di

import com.techskaud.sampleapp.api_services.ApiInterface
import com.techskaud.sampleapp.repository.BaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        retrofit: ApiInterface,

    ): BaseRepository {
        return BaseRepository(retrofit)
    }
}
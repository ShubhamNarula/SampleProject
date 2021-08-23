package com.techskaud.sampleapp.di

import android.content.Context
import androidx.room.Room
import com.techskaud.sampleapp.api_services.ApiInterface
import com.techskaud.sampleapp.repository.BaseRepository
import com.techskaud.sampleapp.repository.SignUpRepository
import com.techskaud.sampleapp.room_db.ApplicationDatabase
import com.techskaud.sampleapp.room_db.SignUpDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    @Singleton
    @Provides
    fun provideData(@ApplicationContext context: Context) : ApplicationDatabase =
        Room.databaseBuilder(context,ApplicationDatabase::class.java,"app_db").allowMainThreadQueries()
            .build()
    @Provides
    fun providesDao(applicationDatabase: ApplicationDatabase):SignUpDao=
        applicationDatabase.signUpDao()

    @Provides
    fun signupRepo(signUpDao: SignUpDao):SignUpRepository =
        SignUpRepository(signUpDao)



}
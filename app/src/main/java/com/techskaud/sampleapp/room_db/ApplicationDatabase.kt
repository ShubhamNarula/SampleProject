package com.techskaud.sampleapp.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.techskaud.sampleapp.response_model.SignUpModel

@Database(entities = [SignUpModel::class],version = 1,exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase()  {
    abstract fun  signUpDao () : SignUpDao
}
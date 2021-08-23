package com.techskaud.sampleapp.room_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.techskaud.sampleapp.response_model.SignUpModel

@Dao
interface SignUpDao {

    @Insert
    fun insert(singUp: SignUpModel)

    @Query("select * from user_db")
    fun getUserData() : List<SignUpModel>
}
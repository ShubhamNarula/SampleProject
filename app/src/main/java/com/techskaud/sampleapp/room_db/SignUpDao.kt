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

    @Query("select * from user_db where phoneNumber =:phoneNo")
    fun getSpecificUserData(phoneNo:String) : SignUpModel

    @Query("Update user_db set password =:password where phoneNumber=:phoneNo")
    fun updatePassword(password:String,phoneNo:String)

    @Query("Update user_db set firstName =:firstName,lastName =:lastName,countryName =:countryName,userImage =:userImage where phoneNumber=:phoneNo")
    fun updateUserProfile(firstName:String,lastName:String,countryName:String,userImage:String,phoneNo:String)
}
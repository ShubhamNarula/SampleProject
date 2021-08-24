package com.techskaud.sampleapp.repository

import com.techskaud.sampleapp.response_model.SignUpModel
import com.techskaud.sampleapp.room_db.SignUpDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

public class SignUpRepository @Inject constructor(
    private val signUpDao: SignUpDao
) {

    val getData : List<SignUpModel> = signUpDao.getUserData()

     fun getUserdata(phoneNumber: String) :SignUpModel{
        return signUpDao.getSpecificUserData(phoneNumber)
    }

    fun updatePassword(password:String,phoneNumber: String){
         signUpDao.updatePassword(password,phoneNumber)
    }

    fun updateUserProfile(firstName:String,lastName:String,country:String,userImage:String,phoneNumber: String){
        signUpDao.updateUserProfile(firstName,lastName,country,userImage,phoneNumber)
    }

    suspend fun insert(singUpModel: SignUpModel) = withContext(Dispatchers.IO){
        signUpDao.insert(singUpModel)
    }

}
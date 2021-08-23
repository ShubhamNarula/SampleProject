package com.techskaud.sampleapp.response_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_db")
public class SignUpModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    @field:SerializedName("email_id")
    var emailId: String ?=null,
    @field:SerializedName("phone_no")
    var phoneNumber:String?=null,
    @field:SerializedName("password")
    var password:String?=null
        )

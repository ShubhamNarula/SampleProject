package com.techskaud.sampleapp.response_model

import android.os.Parcel
import android.os.Parcelable
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
    var password:String?=null,
    @field:SerializedName("first_name")
    var firstName:String?=null,
    @field:SerializedName("last_name")
    var lastName:String?=null,
    @field:SerializedName("country_name")
    var countryName:String?=null,
    @field:SerializedName("user_image")
    var userImage:String?=null
        ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(emailId)
        parcel.writeString(phoneNumber)
        parcel.writeString(password)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(countryName)
        parcel.writeString(userImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SignUpModel> {
        override fun createFromParcel(parcel: Parcel): SignUpModel {
            return SignUpModel(parcel)
        }

        override fun newArray(size: Int): Array<SignUpModel?> {
            return arrayOfNulls(size)
        }
    }
}

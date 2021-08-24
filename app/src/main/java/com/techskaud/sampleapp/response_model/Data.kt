package com.techskaud.sampleapp.response_model

import android.os.Parcel
import android.os.Parcelable
import com.techskaud.sampleapp.adapter.AbstractModel

data class Data(
    var avatar: String?,
    var email: String?,
    var first_name: String?,
    var id: Int?,
    var last_name: String?
): AbstractModel() {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Data> {
        override fun createFromParcel(parcel: Parcel): Data {
            return Data(parcel)
        }

        override fun newArray(size: Int): Array<Data?> {
            return arrayOfNulls(size)
        }
    }
}
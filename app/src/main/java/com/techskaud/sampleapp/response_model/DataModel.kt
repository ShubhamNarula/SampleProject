package com.techskaud.sampleapp.response_model

import android.os.Parcel
import android.os.Parcelable
import com.techskaud.sampleapp.adapter.AbstractModel


data class DataModel(
    val id: Int,
    val title:String,
    val body: String?
        ): AbstractModel() {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
    }

    companion object CREATOR : Parcelable.Creator<DataModel> {
        override fun createFromParcel(parcel: Parcel): DataModel {
            return DataModel(parcel)
        }

        override fun newArray(size: Int): Array<DataModel?> {
            return arrayOfNulls(size)
        }
    }
}

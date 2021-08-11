package com.techskaud.sampleapp.utilities

import android.content.Context

sealed class DataState<out R>(showProgress: Unit) {
    data class Success <out T>(val data: List<T>) : DataState<T>(Utils.hideProgress())
    data class Error(val exception: Exception,val context: Context) : DataState<Nothing>(Utils.hideProgress())
    data  class Loading(val context: Context) : DataState<Nothing>(
        Utils.showProgress(context)
    )
}
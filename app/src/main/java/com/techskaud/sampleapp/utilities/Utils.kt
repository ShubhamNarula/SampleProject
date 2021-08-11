package com.techskaud.sampleapp.utilities

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Toast
import com.techskaud.sampleapp.BaseApplication
import com.techskaud.sampleapp.R

object Utils {
    /**
     * To show progress
     */
    var mProgressDialog: Dialog? = null

    fun showProgress(context: Context) {
        if (mProgressDialog == null) {
            mProgressDialog = Dialog(context,android.R.style.Theme_Translucent)
            mProgressDialog?.window!!.requestFeature(Window.FEATURE_NO_TITLE)
            mProgressDialog?.setContentView(R.layout.loader_half__layout)
            mProgressDialog?.setCancelable(false)
        }
        mProgressDialog?.show()
    }

    fun hideProgress() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog?.dismiss()
        }
    }

    fun showToast(message:String){
        Toast.makeText(BaseApplication.getContext(),message,Toast.LENGTH_SHORT).show()
    }
}
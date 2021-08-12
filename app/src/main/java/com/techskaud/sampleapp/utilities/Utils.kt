package com.techskaud.sampleapp.utilities

import android.app.Activity
import android.app.Application
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
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

    //this method is used to hide the keyboard
    fun hideKeyboard(activity: Activity) {
        val view = activity.findViewById<View>(android.R.id.content)
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
    /** Internet Connection Detector */
    fun Context.isNetworkAvailable(): Boolean {
        val service = Context.CONNECTIVITY_SERVICE
        val manager = getSystemService(service) as ConnectivityManager?
        val network = manager?.activeNetworkInfo
        return (network != null)
    }
}
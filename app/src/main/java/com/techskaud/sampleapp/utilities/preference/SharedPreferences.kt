package com.wh.woohoo.utils.sharedPref
import android.content.Context

/**
 * Created by android on 27/2/18.
 */
 class SharedPreferences {
    companion object {

        var prefs: SharedPreferencesEncrypted? = null
        fun initPreferences(context: Context) {
            prefs = SharedPreferencesEncrypted(context)
        }
    }
}
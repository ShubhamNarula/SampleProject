package com.example.woohoo.base

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.techskaud.sampleapp.R


abstract class BaseActivity : AppCompatActivity() {

    /**
     * Called from onCreate () Function
     */
    public abstract fun getLayoutId(): Int
    public abstract fun onLayoutCreated()

    /**************************************  Activity Lifecycle Methods  ************************************************************************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        // Make sure this is before calling super.onCreate
        setTheme(R.style.MyAppTheme)
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(getLayoutId())
        onLayoutCreated()





    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // pass activity's  result to the fragments
        val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        fragment?.onActivityResult(requestCode, resultCode, data)

    }

}
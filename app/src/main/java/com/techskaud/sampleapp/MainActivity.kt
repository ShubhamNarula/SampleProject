package com.techskaud.sampleapp

import com.example.woohoo.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return  (R.layout.activity_main)
    }

    override fun onLayoutCreated() {

    }



}
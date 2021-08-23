package com.techskaud.sampleapp.fragment.login

import android.view.View
import androidx.lifecycle.ViewModel
import com.techskaud.sampleapp.R
import com.wh.woohoo.utils.extensionFunction.navigateWithId
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(): ViewModel() {

    fun onClick(view:View){
        when(view.id){
            R.id.tvSignup ->{
                view.navigateWithId(R.id.action_loginFragment_to_signInFragment)
            }
        }
    }

}
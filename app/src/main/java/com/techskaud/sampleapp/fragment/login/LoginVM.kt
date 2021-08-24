package com.techskaud.sampleapp.fragment.login

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.techskaud.sampleapp.BaseApplication
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.repository.SignUpRepository
import com.techskaud.sampleapp.response_model.SignUpModel
import com.techskaud.sampleapp.utilities.Constants
import com.techskaud.sampleapp.utilities.Utils
import com.template.validations.Validation
import com.wh.woohoo.utils.extensionFunction.navigateWithId
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
public class LoginVM @Inject constructor(
     val signUpRepository: SignUpRepository
): ViewModel() {

    val userPhone by lazy { ObservableField("") }
    val userPass by lazy { ObservableField("") }

    fun onClick(view:View){
        when(view.id){
            R.id.tvSignup ->{
                view.navigateWithId(R.id.action_loginFragment_to_signInFragment)
            }
            R.id.tvForgotPassword ->{
                view.navigateWithId(R.id.action_loginFragment_to_forgotPasswordFragment)
            }
            R.id.btSignIn ->{
                val context = BaseApplication.getContext()
                val validation = Validation.create(context).apply {
                    isEmpty(userPhone.get(), context.getString(R.string.enter_phone) ?: "")
                    isEmpty(userPass.get(), context.getString(R.string.enter_password) ?: "")

                }
                if (validation.isValid()) {
                    var flag=false
                    val data = signUpRepository.getUserdata(userPhone.get().toString())
                    if (data!=null) {
                        if (userPhone.get() == data.phoneNumber && userPass.get() == data.password){
                            flag=true
                        }
                    }
                    if (flag) {
                        Utils.showToast("Login Successfully.")
                        val bundle = Bundle()
                        bundle.putParcelable(Constants.DATA,data)
                        view.navigateWithId(R.id.action_loginFragment_to_userProfile,bundle)
                    } else {
                        Utils.showToast("Enter Valid PhoneNumber Or Password.")
                    }
                }


            }

        }
    }

}
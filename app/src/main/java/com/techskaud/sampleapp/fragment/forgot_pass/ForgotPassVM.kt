package com.techskaud.sampleapp.fragment.forgot_pass

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.techskaud.sampleapp.BaseApplication
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.repository.SignUpRepository
import com.techskaud.sampleapp.utilities.Utils
import com.techskaud.sampleapp.utilities.validation.ValidatorUtils
import com.wh.woohoo.utils.extensionFunction.navigateBack
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
public class ForgotPassVM @Inject constructor(
    val signUpRepository: SignUpRepository
) : ViewModel() {
    val userPhoneNumber by lazy { ObservableField("") }
    val password by lazy { ObservableField("") }


    fun onClick(view:View){
        when(view.id){
            R.id.back ->{
                view.navigateBack()
            }
            R.id.btSubmit ->{
                val context = BaseApplication.getContext()
                when {
                    userPhoneNumber.get()?.trim()?.isEmpty() == true ->
                        Utils.showToast(context.getString(R.string.enter_phone))

                            !ValidatorUtils.isMobileValid(userPhoneNumber.get() ?: "") ->
                        Utils.showToast(context.getString(R.string.enter_valid_phone))
                    password.get()?.trim()?.isEmpty() == true ->
                        Utils.showToast(context.getString(R.string.enter_password))

                    else -> {
                        val data = signUpRepository.getUserdata(userPhoneNumber.get().toString())
                        if (data!=null){
                            signUpRepository.updatePassword(password = password.get().toString(),phoneNumber = userPhoneNumber.get().toString())
                            Utils.showToast("Password update successfully.")
                            view.navigateBack()
                        }else{
                            Utils.showToast("User not exist.")
                        }
                    }

                }
            }
        }
    }
}
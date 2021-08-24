package com.techskaud.sampleapp.fragment.sign_in

import android.content.Context
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techskaud.sampleapp.BaseApplication
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.repository.SignUpRepository
import com.techskaud.sampleapp.response_model.SignUpModel
import com.techskaud.sampleapp.utilities.Utils
import com.techskaud.sampleapp.viewmodel.BaseViewModel
import com.template.validations.Validation
import com.wh.woohoo.utils.extensionFunction.navigateBack
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class SignUpVM @Inject constructor(
    public val signUpRepository: SignUpRepository
) : ViewModel() {
    val email by lazy { ObservableField("") }
    val phone by lazy { ObservableField("") }
    val password by lazy { ObservableField("") }
    val confirmPassword by lazy { ObservableField("") }


    //this is used for data insert in background
    fun insert(signUpModel: SignUpModel) = viewModelScope.launch {
        signUpRepository.insert(signUpModel)
    }



     fun onClick(view: View){
        when(view.id){
            R.id.back,R.id.tvGotoSignIn ->{
                view.navigateBack()
            }

            R.id.btSignUp -> {
                val context = BaseApplication.getContext()
                val validation = Validation.create(context).apply {
                    isEmpty(email.get(), context.getString(R.string.enter_email) ?: "")
                    isEmailValid(email.get(), context.getString(R.string.enter_valid_email) ?: "")

                    isEmpty(phone.get(), context.getString(R.string.enter_phone) ?: "")
                    isPhoneValid(phone.get(), context.getString(R.string.enter_valid_phone) ?: "")
                    isEmpty(password.get(), context.getString(R.string.enter_password) ?: "")
                    isEmpty(
                        confirmPassword.get(),
                        context.getString(R.string.enter_confirm_password) ?: ""
                    )
                    areEqual(
                        confirmPassword.get(),
                        password.get(),
                        context.getString(R.string.password_does_not_match) ?: ""
                    )
                }
                if (validation.isValid()) {
                    val signUpModel = SignUpModel(
                        emailId = email.get(),
                        phoneNumber =phone.get(), password = password.get()
                    )
                  val data = signUpRepository.getUserdata(phone.get().toString())
                    if (data!=null) {
                        if (data.phoneNumber == phone.get()) {
                            return Utils.showToast("User Already Exist.")
                        }
                    }

                    insert(signUpModel)
                    Utils.showToast("SignUp Successfully.")
                    view.navigateBack()
                }
            }

        }
    }


}
package com.techskaud.sampleapp.fragment

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.view.View
import androidx.annotation.RequiresApi
import com.example.woohoo.base.BaseFragment
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.utilities.Constants
import com.techskaud.sampleapp.utilities.Utils
import com.techskaud.sampleapp.utilities.dialoge.ImagePickerDialog
import com.template.permissionsutil.PermissionsUtil.arePermissionsGranted
import com.template.permissionsutil.PermissionsUtil.requestFragmentPermission
import kotlinx.android.synthetic.main.userprofile_fragment.*

class UserProfile : BaseFragment(), View.OnClickListener {
    override fun getLayoutID(): Int {
        return R.layout.userprofile_fragment
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView() {
        init()
        clickEvents()

    }
     @RequiresApi(Build.VERSION_CODES.M)
     fun init(){
         requireActivity().requestFragmentPermission(this@UserProfile,Constants.CAMERA_GALLERY_REQUEST)
     }
    fun clickEvents(){
        imgBack.setOnClickListener(this)
        img_user.setOnClickListener(this)
        img_camera.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.imgBack ->{
                goBack()
            }
            R.id.img_user,R.id.img_camera ->{
                if (requireActivity().arePermissionsGranted(Constants.CAMERA_GALLERY_REQUEST)){
                    childFragmentManager.let {
                        ImagePickerDialog.newInstance().show(it, "ImagePicker")
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK
            && requestCode == Constants.REQUEST_CAMERA
            && data != null
        ) {
            val userImage = data.extras?.get("data") as Bitmap
            img_user.setImageBitmap(userImage)

        }
    }
    }
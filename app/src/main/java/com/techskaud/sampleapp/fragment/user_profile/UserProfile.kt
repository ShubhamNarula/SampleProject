package com.techskaud.sampleapp.fragment.user_profile

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.woohoo.base.BaseFragment
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.utilities.Constants
import com.techskaud.sampleapp.utilities.ImageUtils
import com.techskaud.sampleapp.utilities.Utils
import com.techskaud.sampleapp.utilities.dialoge.ImagePickerDialog
import com.template.permissionsutil.PermissionsUtil.arePermissionsGranted
import com.template.permissionsutil.PermissionsUtil.requestFragmentPermission
import com.template.validations.Validation
import com.wh.woohoo.utils.extensionFunction.loadImg
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
        btSubmit.setOnClickListener(this)
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
                        ImagePickerDialog.newInstance { requestCode ->
                            if (requestCode == Constants.REQUEST_CAMERA) {
                                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                                try {
                                    resultLauncher.launch(takePictureIntent)
                                } catch (e: ActivityNotFoundException) {
                                    // display error state to the user
                                }

                            }else {
                                val i = Intent(
                                    Intent.ACTION_PICK,
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                                )
                                resultLauncher.launch(i)
                            }

                        }.show(it, "ImagePicker")
                    }
                }
            }
            R.id.btSubmit ->{
                val validation = Validation.create(mActivity!!).apply {
                    isEmpty(et_first_name.text.toString(), context?.getString(R.string.enter_first_name) ?: "")

                }
                if (validation.isValid()) {
                    //hit api
                }
            }
        }
    }
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            val userImage = data?.extras?.get("data")
            if (userImage is Bitmap){
                img_user.setImageBitmap(userImage)
                val originalImagePath = Utils.bitmapToFile(userImage)

            }else{
                img_user.loadImg(data?.data.toString(),mActivity!!)
               val original =  ImageUtils.createCopyAndReturnRealPath(data?.data!!)
                Log.e("Fetch_Data", "data: $original", )
            }
        }
    }

    }
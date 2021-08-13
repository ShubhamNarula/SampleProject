package com.techskaud.sampleapp.utilities.dialoge

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.fragment.app.DialogFragment
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.utilities.Constants
import kotlinx.android.synthetic.main.dialog_image_picker.*
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.fragment.app.Fragment


class ImagePickerDialog () : DialogFragment(), View.OnClickListener {

    companion object {
        lateinit var buttonClickCallBack : (Int) ->Unit
        fun newInstance(buttonClickCallBack: (Int) -> Unit): ImagePickerDialog {
            this.buttonClickCallBack=buttonClickCallBack
            return ImagePickerDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogStyle)

    }


    override fun onActivityCreated(arg0: Bundle?) {
        super.onActivityCreated(arg0)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogZoomAnim

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        isCancelable = false
        return inflater.inflate(R.layout.dialog_image_picker, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListener()
    }

    fun setListener() {
        txt_dialog_take_photo.setOnClickListener(this)
        txt_dialog_choose_photo.setOnClickListener(this)
        txt_dialog_close.setOnClickListener {
            dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        val window = dialog?.window
        val size = Point()
        val display = window?.windowManager?.defaultDisplay
        display?.getSize(size)
        val width = size.x
        window?.setLayout((width * 0.85).toInt(), WindowManager.LayoutParams.WRAP_CONTENT)
        window?.setGravity(Gravity.CENTER)
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.txt_dialog_take_photo -> {
//                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                try {
//                    activity?.startActivityForResult(takePictureIntent, Constants.REQUEST_CAMERA)
//                } catch (e: ActivityNotFoundException) {
//                    // display error state to the user
//                }
                buttonClickCallBack(Constants.REQUEST_CAMERA)
                dismiss()
            }
            R.id.txt_dialog_choose_photo -> {
                buttonClickCallBack(Constants.REQUEST_GALLERY)
//                val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//                activity?.startActivityForResult(i, Constants.REQUEST_GALLERY)
                dismiss()
            }
        }
    }


}
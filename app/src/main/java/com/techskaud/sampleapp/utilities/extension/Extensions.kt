package com.wh.woohoo.utils.extensionFunction

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.techskaud.sampleapp.BaseApplication
import com.template.datastore.DATA_STORE_NAME

/*Use to set recycler adapter view*/
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

val Context.dataStore by preferencesDataStore(name = DATA_STORE_NAME)


/*Log print*/
fun Any.error(message: String) {
    Log.e(this::class.java.simpleName, message)
}

/**
 * Navigate using destination ID
 * */
fun View.navigateWithId(id: Int, bundle: Bundle?=null) = try {
    this.findNavController().navigate(id, bundle)
} catch (e: Exception) {
    e.printStackTrace()
}

/**
 * Navigate using Nav Directions
 * */
fun View.navigateWithAction(action: NavDirections) = try {
    this.findNavController().navigate(action)
} catch (e: Exception) {
    e.printStackTrace()
}

/**
 * Navigate to previous screen
 * */
fun View.navigateBack() = try {
    this.findNavController().navigateUp()
} catch (e: Exception) {
    e.printStackTrace()
}


//for circle image
fun ImageView.loadImg(url: String, activity: Context) {
    Glide.with(activity)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .thumbnail(0.1f)
        //   .placeholder(R.mipmap.ic_user_profile_holder)//place holder user according to own need
        .dontAnimate()
        .circleCrop()
        .signature {(System.currentTimeMillis().toString()) }
        .into(this)
}



//for simple image
fun ImageView.imageLoad(url: String, activity: Context) {
    Glide.with(activity)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .thumbnail(0.1f)
        .dontAnimate()
        .signature {(System.currentTimeMillis().toString()) }
        .into(this)
}
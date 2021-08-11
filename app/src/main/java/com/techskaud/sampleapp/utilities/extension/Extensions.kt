package com.wh.woohoo.utils.extensionFunction

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

/*Use to set recycler adapter view*/
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

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



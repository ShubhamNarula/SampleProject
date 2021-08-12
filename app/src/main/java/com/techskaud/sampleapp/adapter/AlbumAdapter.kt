package com.techskaud.sampleapp.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.response_model.AlbumModel
import com.wh.woohoo.utils.extensionFunction.inflate
import com.wh.woohoo.utils.extensionFunction.loadImg
import kotlinx.android.synthetic.main.item_view_data.view.*

class AlbumAdapter (val mList : List<AlbumModel>, val onClickItem : (data: AlbumModel) -> Unit) : RecyclerView.Adapter<AlbumAdapter.ViewHolderData>() {
   var context : Context?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderData {
        context=parent.context
        return ViewHolderData (parent.inflate(R.layout.item_view_data,false))
    }

    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        holder.setData(mList[position])

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolderData(val view: View): RecyclerView.ViewHolder(view){
        fun setData(data: AlbumModel){
            view.txt_user_title.text = data.title
            view.img_profile.loadImg(data.url,context!!)
            view.cl_item.setOnClickListener {
                onClickItem(data)
            }
        }
    }

}
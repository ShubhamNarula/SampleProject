package com.techskaud.sampleapp.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.response_model.DataModel
import com.wh.woohoo.utils.extensionFunction.inflate
import kotlinx.android.synthetic.main.item_view_data.view.*


class DataAdapter (val mList : List<DataModel>,val onClickItem : (data:DataModel) -> Unit) : RecyclerView.Adapter<DataAdapter.ViewHolderData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderData {
      return ViewHolderData (parent.inflate(R.layout.item_view_data,false))
    }

    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        holder.setData(mList[position])

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolderData(val view: View):RecyclerView.ViewHolder(view){
           fun setData(data:DataModel){
               view.txt_user_title.text = data.title
               view.txt_body.text = data.body
               view.cl_item.setOnClickListener {
                   onClickItem(data)
               }
           }
    }

}
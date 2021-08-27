package com.techskaud.sampleapp.ktor_

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.techskaud.sampleapp.databinding.ItemViewDataBinding
import com.techskaud.sampleapp.response_model.DataModel
import javax.inject.Inject

class PostAdapter
@Inject
constructor() : ListAdapter<DataModel, PostAdapter.PostViewHolder>(Diff) {

    class PostViewHolder(private val binding: ItemViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: DataModel) {
            binding.apply {
                txtBody.text = post.body
                txtUserTitle.text = post.title
            }
        }
    }

    object Diff : DiffUtil.ItemCallback<DataModel>() {
        override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemViewDataBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        if (post != null) {
            holder.bind(post)
        }
    }
}
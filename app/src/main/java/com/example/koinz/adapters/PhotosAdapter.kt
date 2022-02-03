package com.example.koinz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.koinz.BR
import com.example.koinz.R
import com.example.koinz.data.Photo
import com.example.koinz.databinding.PhotosItemBinding

class PhotosAdapter : PagingDataAdapter<Photo, PhotosAdapter.PhotosHolder>(Photo_COMPARATOR) {

    companion object {
        private val Photo_COMPARATOR = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem == newItem
        }
    }

    class PhotosHolder(bindingItem: PhotosItemBinding) : RecyclerView.ViewHolder(bindingItem.root) {

        private val binding = bindingItem
        fun bind(obj: Any) {
            binding.setVariable(BR.photoModel, obj)
            binding.executePendingBindings()
        }

    }


    override fun onBindViewHolder(holder: PhotosHolder, position: Int) {

        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PhotosHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.photos_item, parent, false
            )
        )

}
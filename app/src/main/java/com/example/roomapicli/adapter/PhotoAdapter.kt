package com.example.roomapicli.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.model.Photo
import com.example.roomapicli.R
import com.example.roomapicli.databinding.ItemPhotoBinding
import com.example.roomapicli.viewmodel.ItemsPhotoViewModel

class PhotoAdapter(listPhotos: List<Photo>, val liveData: MutableLiveData<Photo>) :
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    private val listPhotoData = listPhotos

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false))

    override fun getItemCount() = listPhotoData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listPhotoData[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding: ItemPhotoBinding? = DataBindingUtil.bind(view)

        fun bind(item: Photo) {
            binding?.itemsPhotoViewModel = ItemsPhotoViewModel(item, liveData)
            binding?.imagePhoto?.context?.let {
                Glide.with(it).load(item.url).into(binding.imagePhoto)
            }
        }
    }
}
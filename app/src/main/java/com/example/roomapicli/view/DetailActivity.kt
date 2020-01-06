package com.example.roomapicli.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.core.model.Photo


import com.example.roomapicli.R
import com.example.roomapicli.databinding.ActivityDetailBinding

import com.example.roomapicli.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private  lateinit var  binding : ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private var photos: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundleReceived = intent.extras
        bundleReceived?.getString(BUNDLE_KEY).let { photos = it }
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_detail
        )
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        binding.detailViewModel = detailViewModel
        setImageUrl(binding.detailedImage, photos)
    }
    private fun setImageUrl(view: ImageView, detailModel: String?) {
        Glide.with(view.context).load(detailModel)
            .into(view)
    }

    companion object{
        const val BUNDLE_KEY = "details"
    }
}

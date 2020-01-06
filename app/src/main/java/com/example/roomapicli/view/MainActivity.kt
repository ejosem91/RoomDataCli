package com.example.roomapicli.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.model.Photo
import com.example.roomapicli.R
import com.example.roomapicli.adapter.PhotoAdapter
import com.example.roomapicli.databinding.ActivityMainBinding
import com.example.roomapicli.util.Initialize
import com.example.roomapicli.viewmodel.FactoryPhotoViewModel
import com.example.roomapicli.viewmodel.PhotoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PhotoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModelFactory = FactoryPhotoViewModel((application as Initialize).useCase)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PhotoViewModel::class.java)
        binding.viewModel = viewModel
        show()
        viewModel.liveDataEvent.observe(this, clickObserver())
    }

    private fun show() {
        viewModel.photoListData.observe(this, Observer {
            val adapterPhotos = PhotoAdapter(it, viewModel.liveDataEvent)
            binding.photoRecycler.layoutManager = LinearLayoutManager(this)
            binding.photoRecycler.adapter = adapterPhotos
        })
    }

    private fun clickObserver() = Observer<Photo> {
        val detailClassIntent = Intent(this, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putString(BUNDLE_KEY, it.url)
        detailClassIntent.putExtras(bundle)
        ContextCompat.startActivity(this, detailClassIntent, null)
    }

    companion object {
        const val BUNDLE_KEY = "details"
    }
}

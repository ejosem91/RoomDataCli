package com.example.roomapicli.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.model.Photo
import com.example.roomapicli.R
import com.example.roomapicli.adapter.PhotoAdapter
import com.example.roomapicli.databinding.ActivityMainBinding
import com.example.roomapicli.util.Event
import com.example.roomapicli.viewmodel.PhotoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModel<PhotoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = viewModel

        show()
        viewModel.getPhotoList()
        viewModel.liveDataEvent.observe(this, clickObserver())
    }

    private fun show() {
        viewModel.photoListData.observe(this, Observer {
            val adapterPhotos = PhotoAdapter(it, viewModel.liveDataEvent)
            binding.photoRecycler.layoutManager = LinearLayoutManager(this)
            binding.photoRecycler.adapter = adapterPhotos
        })
    }

    private fun clickObserver() = Observer<Event<Photo>> { event ->
        event.getContentIfNotHandled()?.let { photo ->
            val detailClassIntent = Intent(this, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putString(BUNDLE_KEY, photo.url)
            detailClassIntent.putExtras(bundle)
            ContextCompat.startActivity(this, detailClassIntent, null)
        }
    }

    companion object {
        const val BUNDLE_KEY = "details"
    }
}
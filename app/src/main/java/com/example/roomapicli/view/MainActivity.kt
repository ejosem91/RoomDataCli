package com.example.roomapicli.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.roomapicli.R
import com.example.roomapicli.api.Api
import com.example.roomapicli.databinding.ActivityMainBinding
import com.example.roomapicli.model.Photo
import com.example.roomapicli.viewmodel.PhotoModelView
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PhotoModelView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(PhotoModelView::class.java)

        binding.viewModel = viewModel
        showData()
    }

    private fun showData() {
        binding.viewModel?.getListPhoto()?.observe(this, Observer {
            Log.v("MainActivity", " ${it.size}")
        })

    }


   /* private fun getData() {
        val call: Call<List<Photo>> = Api.retrofit().getPhotos()
        call.enqueue(object : retrofit2.Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>?, response: Response<List<Photo>>?) {
                Log.v("MainActivity", "${response?.body()?.size} ")

            }

            override fun onFailure(call: Call<List<Photo>>?, t: Throwable?) {
            }
        })
    }*/

}

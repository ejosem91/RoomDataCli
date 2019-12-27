package com.example.roomapicli.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapicli.R
import com.example.roomapicli.adapter.PhotoAdapter
import com.example.roomapicli.database.PhotoDao
import com.example.roomapicli.database.PhotoDataBase
import com.example.roomapicli.databinding.ActivityMainBinding
import com.example.roomapicli.model.Photo
import com.example.roomapicli.repository.RepositoryGetPhoto
import com.example.roomapicli.viewmodel.FactoryPhotoViewModel
import com.example.roomapicli.viewmodel.PhotoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PhotoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val db :PhotoDataBase?  = PhotoDataBase.getInstance(context =  applicationContext)
        //val photoDao : PhotoDao? = db?.photoDao()

        val viewModelFactory = FactoryPhotoViewModel(RepositoryGetPhoto(db))

        viewModel = ViewModelProviders.of(this,viewModelFactory).get(PhotoViewModel::class.java)
        binding.viewModel = viewModel
        show()
        viewModel.liveData.observe( this , clickObserver() )
    }

    private fun show() {
        viewModel?.photoListData?.observe(this, Observer {
            val adapterPhotos = PhotoAdapter(it,viewModel.liveData)
            binding.photoRecycler.layoutManager = LinearLayoutManager(this)
            binding.photoRecycler.adapter = adapterPhotos
        })

    }

    private  fun clickObserver() = Observer<Photo> {
        val detailClassIntent = Intent(
            this,
            DetailActivity::class.java
        )
        val bundle = Bundle()
        bundle.putParcelable(BUNDLE_KEY, it)
        detailClassIntent.putExtras(bundle)
        ContextCompat.startActivity(this, detailClassIntent, null)

        it.let {
            Log.v("LogWF", "Click")
        }
    }

    companion object{
        const val BUNDLE_KEY = "details"
    }
}

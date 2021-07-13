package com.example.iphonesearchapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iphonesearchapi.adapter.ItunesAdapter
import com.example.iphonesearchapi.databinding.ActivityMainBinding
import com.example.iphonesearchapi.model.Status
import com.example.iphonesearchapi.viewmodel.ItunesViewModel

private lateinit var binding: ActivityMainBinding
const val BaseUrl = "https://itunes.apple.com/"

class MainActivity : AppCompatActivity() {

    private val viewModel: ItunesViewModel by viewModels()

        fun showLoadingIndicator(showLoading: Boolean){
           binding.progressBar.visibility = if (showLoading) View.VISIBLE else View.INVISIBLE
        }



    private val itunesAdapter: ItunesAdapter by lazy {
        ItunesAdapter( mutableListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val linearLayoutManager = LinearLayoutManager(this)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.adapter = itunesAdapter
        viewModel.itunesLiveData.observe(this, Observer { weathersResource ->
            when (weathersResource.status) {
                Status.Success -> {
                    showLoadingIndicator(false)
                    itunesAdapter.resetDataSource(weathersResource.data ?: listOf())
                }
                Status.Loading -> {
                    showLoadingIndicator(true)
                    itunesAdapter.resetDataSource(weathersResource.data ?: listOf())
                }
            }
        })




    }

    override fun onResume() {
        super.onResume()
        viewModel.triggerItunesapi()

    }

}

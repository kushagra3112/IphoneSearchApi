package com.example.iphonesearchapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iphonesearchapi.adapter.ItunesAdapter
import com.example.iphonesearchapi.databinding.ActivityMainBinding
import com.example.iphonesearchapi.model.ResultOf
import com.example.iphonesearchapi.utility.showLoadingIndicator
import com.example.iphonesearchapi.viewmodel.ItunesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: ItunesViewModel by viewModel()

    private val itunesAdapter: ItunesAdapter by lazy {
        ItunesAdapter(mutableListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = itunesAdapter
        viewModel.itunes.observe(this, { resourse ->
            when (resourse) {
                is ResultOf.Loading -> {
                    showLoadingIndicator(true, binding.progressBar)
                }
                is ResultOf.Failure -> {
                }
                is ResultOf.Success -> {
                    showLoadingIndicator(false, binding.progressBar)
                    itunesAdapter.resetDataSource(resourse.value)
                }
            }
        })


    }

    override fun onResume() {
        super.onResume()
        viewModel.triggerItunesapi()

    }

}

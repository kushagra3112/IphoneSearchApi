package com.example.iphonesearchapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iphonesearchapi.network.IphoneApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iphonesearchapi.adapter.ItunesAdapter
import com.example.iphonesearchapi.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import java.util.ArrayList

private lateinit var binding: ActivityMainBinding
const val BaseUrl = "https://itunes.apple.com/"
var ituneslist = ArrayList<String>()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val linearLayoutManager = LinearLayoutManager(this)
        setContentView( binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(IphoneApiService::class.java)

        lifecycleScope.launch {

            val call = service.getResult("Imagine Dragons")

            call.body()?.results?.forEach { result ->
                ituneslist.add(result.trackName)
            }
            val adapter = ItunesAdapter(ituneslist)
            binding.recyclerView.layoutManager = linearLayoutManager
            binding.recyclerView.adapter = adapter
        }
    }


}

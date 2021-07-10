package com.example.iphonesearchapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.LifecycleOwner
import com.example.iphonesearchapi.network.IphoneApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import androidx.lifecycle.lifecycleScope
import com.example.iphonesearchapi.adapter.ItunesAdapter
import com.example.iphonesearchapi.databinding.ActivityMainBinding
import com.example.iphonesearchapi.model.Result
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import kotlin.math.log

private lateinit var binding: ActivityMainBinding
const val BaseUrl = "https://itunes.apple.com/"
var ituneslist = ArrayList<String>()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(IphoneApiService::class.java)

        lifecycleScope.launch {

            val call = service.getResult("Imagine Dragons")

            call.body()?.results?.forEach { result ->
                println(result.artistName)
                ituneslist.add(result.artistName)
            }
            var adapter = ItunesAdapter(this@MainActivity, ituneslist)
            binding.grid.adapter = adapter
        }
    }


}

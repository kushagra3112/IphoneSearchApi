package com.example.iphonesearchapi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.example.iphonesearchapi.BaseUrl
import com.example.iphonesearchapi.model.ITunesResponse
import com.example.iphonesearchapi.model.Resource
import com.example.iphonesearchapi.model.Result
import com.example.iphonesearchapi.network.IphoneApiService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItunesViewModel(application: Application) : AndroidViewModel(application) {

    val retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service: IphoneApiService = retrofit.create(IphoneApiService::class.java)
    val itunesLiveData = MutableLiveData(Resource.success(listOf<Result>()))
    val ituneslist = itunesLiveData.value!!.data!!.toMutableList()


    fun triggerItunesapi() {
        viewModelScope.launch {
            itunesLiveData.value = Resource.loading(listOf())
            val call = service.getResult("Imagine Dragons")

            call.body()?.results?.forEach { result ->
                var liveData = ituneslist.find { value -> value == result }
                if (liveData !== null) {
                    liveData = result
                } else {
                    ituneslist.add(result)

                }

                itunesLiveData.value = Resource.loading(ituneslist)
            }
            itunesLiveData.value = Resource.success(ituneslist)

        }


    }
}
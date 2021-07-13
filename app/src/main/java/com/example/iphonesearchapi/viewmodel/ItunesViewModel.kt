package com.example.iphonesearchapi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.example.iphonesearchapi.BaseUrl
import com.example.iphonesearchapi.model.Result
import com.example.iphonesearchapi.model.ResultOf
import com.example.iphonesearchapi.network.IphoneApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class ItunesViewModel(application: Application) : AndroidViewModel(application) {

    val retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service: IphoneApiService = retrofit.create(IphoneApiService::class.java)
    private val _itunes = MutableLiveData<ResultOf<Result>>()
    val itunes: LiveData<ResultOf<Result>> = _itunes


    fun triggerItunesapi() {
        viewModelScope.launch {
            val call = service.getResult("Imagine Dragons")

            call.body()?.results?.forEach { result ->

                try {

                    _itunes.setValue(ResultOf.Loading(result))

                }catch (ioe: IOException){
                    _itunes.postValue(ResultOf.Failure("IO exception",ioe))
                }

            }
            _itunes.postValue(ResultOf.Success)
        }


    }
}
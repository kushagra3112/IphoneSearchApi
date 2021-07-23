package com.example.iphonesearchapi.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.iphonesearchapi.database.SongRepository

import com.example.iphonesearchapi.model.Result
import com.example.iphonesearchapi.model.ResultOf
import com.example.iphonesearchapi.network.CustomApp
import com.example.iphonesearchapi.network.IphoneApiService
import kotlinx.coroutines.launch
import retrofit2.Response

class ItunesViewModel(private val serviceUtil: IphoneApiService, application: Application) : AndroidViewModel(
    application
) {


    private val _itunes = MutableLiveData<ResultOf<MutableList<Result>>>()
    val itunes: LiveData<ResultOf<MutableList<Result>>> = _itunes

    fun triggerItunesapi(searchText: String) {

        _itunes.setValue(ResultOf.Loading)
        viewModelScope.launch {

            val response = serviceUtil.getResult(searchText)

            if (response.isSuccessful) {
                _itunes.setValue(ResultOf.Success(response.body()?.results!!))

            } else {
                _itunes.setValue(ResultOf.Failure("Error"))
            }


        }
    }
}

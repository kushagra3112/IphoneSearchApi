package com.example.iphonesearchapi.viewmodel

import androidx.lifecycle.*

import com.example.iphonesearchapi.model.Result
import com.example.iphonesearchapi.model.ResultOf
import com.example.iphonesearchapi.network.IphoneApiService
import kotlinx.coroutines.launch
import retrofit2.Response

class ItunesViewModel(private val serviceUtil: IphoneApiService) : ViewModel() {


    private val _itunes = MutableLiveData<ResultOf<List<Result>>>()
    val itunes: LiveData<ResultOf<List<Result>>> = _itunes

    fun triggerItunesapi(searchText: String) {

        viewModelScope.launch {
            _itunes.value = ResultOf.Loading
            val response = serviceUtil.getResult(searchText)

            if (response.isSuccessful) {
                _itunes.setValue(ResultOf.Success(response.body()?.results!!))

            } else {
                _itunes.setValue(ResultOf.Failure("Error"))
            }


        }
    }
}

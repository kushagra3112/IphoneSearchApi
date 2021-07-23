package com.example.iphonesearchapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iphonesearchapi.model.Result
import com.example.iphonesearchapi.model.ResultOf
import com.example.iphonesearchapi.network.IphoneApiService
import kotlinx.coroutines.launch

class DetailViewModel(private val serviceUtil: IphoneApiService): ViewModel() {
    private val _songDetails = MutableLiveData<ResultOf<List<Result>>>()
    val songDetails: LiveData<ResultOf<List<Result>>> = _songDetails

    fun triggerItunesapi(trackId: Int) {
        _songDetails.setValue(ResultOf.Loading)
        viewModelScope.launch {
            val response = serviceUtil.getSongDetail(trackId)

            if (response.isSuccessful) {
                _songDetails.setValue(ResultOf.Success(response.body()?.results!!))

            } else {
                _songDetails.setValue(ResultOf.Failure("Error"))
            }


        }
    }
}
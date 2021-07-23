package com.example.iphonesearchapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.iphonesearchapi.database.SongRepository
import com.example.iphonesearchapi.model.ResultOf
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

class SongViewModel(private val repository: SongRepository) : ViewModel() {
    private val _loginUserFlow = MutableStateFlow<ResultOf<List<Long>>>(ResultOf.Loading)
    val loginUserFlow : StateFlow<ResultOf<List<Long>>> =  _loginUserFlow
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(trackId: Long) = viewModelScope.launch {
        repository.insert(trackId)
    }
    fun delete(trackId: Long) = viewModelScope.launch {
        repository.delete(trackId)
    }
    fun observe()=viewModelScope.launch {
        repository.observe()
            .collect {
                _loginUserFlow.value=ResultOf.Success(it)
            }
    }




}


class SongViewModelFactory(private val repository: SongRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SongViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SongViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
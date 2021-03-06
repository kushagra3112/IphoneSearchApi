package com.example.iphonesearchapi.di

import com.example.iphonesearchapi.network.IphoneApiService
import com.example.iphonesearchapi.viewmodel.ItunesViewModel
import com.example.iphonesearchapi.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ItunesModule = module {

    viewModel {
        ItunesViewModel(get())
    }
    viewModel{
        DetailViewModel(get())
    }
}
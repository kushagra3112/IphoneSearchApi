package com.example.iphonesearchapi.di

import com.example.iphonesearchapi.network.IphoneApiService
import com.example.iphonesearchapi.viewmodel.ItunesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ItunesModule = module {

    viewModel {
      ItunesViewModel(get<IphoneApiService>())
    }

}
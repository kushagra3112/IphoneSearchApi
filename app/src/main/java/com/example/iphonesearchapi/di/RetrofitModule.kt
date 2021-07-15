package com.example.iphonesearchapi.di

import com.example.iphonesearchapi.network.IphoneApiService
import com.example.iphonesearchapi.utility.Constants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val RetrofitModule = module {
    single {
        retrofit(Constants.BASE_URL)
    }
    single {
        get<Retrofit>().create(IphoneApiService::class.java)
    }
}

private fun retrofit( baseUrl: String) = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


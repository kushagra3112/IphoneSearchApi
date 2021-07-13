package com.example.iphonesearchapi


import android.app.Application
import com.example.iphonesearchapi.network.IphoneApiService
import com.google.gson.GsonBuilder
import org.kodein.di.*

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://itunes.apple.com/"

class AndroidTemplateApplication: Application(), DIAware {

    override val di by DI.lazy {



        bind<Retrofit>("retrofit") with singleton {
            val gson = GsonBuilder().setLenient().create()
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        bind<IphoneApiService>("apiService") with singleton {
            instance<Retrofit>("retrofit").create(IphoneApiService::class.java)
        }


    }
}

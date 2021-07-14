package com.example.iphonesearchapi.network

import com.example.iphonesearchapi.model.ITunesResponse
import com.example.iphonesearchapi.model.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IphoneApiService {
    @GET("search")
    suspend fun getResult(
        @Query("term") searchTerm: CharSequence
    ): Response<ITunesResponse>
}
package com.example.iphonesearchapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ITunesResponse(
        @SerializedName("results")
        val results: List<Result>
)
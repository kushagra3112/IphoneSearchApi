package com.example.iphonesearchapi.model


sealed class ResultOf<out T>() {
    data class Success<out R>(val value :R): ResultOf<R>()
    data class Failure(
        val message: String?,
    ):ResultOf<Nothing>()
    object Loading : ResultOf<Nothing>()
}

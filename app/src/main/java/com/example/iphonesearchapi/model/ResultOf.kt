package com.example.iphonesearchapi.model


sealed class ResultOf<out T>() {
    data class Loading<out R>(val value :R): ResultOf<R>()
    data class Failure(
        val message: String?,
        val throwable: Throwable?
    ):ResultOf<Nothing>()
    object Success : ResultOf<Nothing>()
}

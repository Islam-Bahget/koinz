package com.example.koinz.network

import okhttp3.ResponseBody
import retrofit2.http.Body

sealed class ResponseResource<out T> {
    data class Success<out T>(val vale: T) : ResponseResource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?

    ) : ResponseResource<Nothing>()
}
package com.example.koinz.repository

import androidx.lifecycle.MutableLiveData
import com.example.koinz.model.PhotosResponse
import com.example.koinz.network.PhotosApi
import com.example.koinz.network.ResponseResource
import com.example.koinz.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosRepo(private val photosApi: PhotosApi) : BaseRepo() {

    suspend fun getPhotos(
        method: String,
        format: String,
        nonJson: String,
        text: String,
        page: Int,
        perPage: Int,
        apiKey: String
    ) = photosApi.getPhotos(method, format, nonJson, text, page, perPage, apiKey)
}
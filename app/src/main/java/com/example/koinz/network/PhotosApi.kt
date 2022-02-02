package com.example.koinz.network

import com.example.koinz.model.PhotosResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {

    @GET("services/rest")
    suspend fun getPhotos(
        @Query("method") method: String,
        @Query("format") format: String,
        @Query("nojsoncallback") noJson: String,
        @Query("text") text: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("api_key") apiKey: String,

        ): PhotosResponse
}
package com.example.koinz.fragments

import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.example.koinz.paging.PhotoDataSource
import com.example.koinz.data.PhotosDataBase
import com.example.koinz.network.PhotosApi
import com.example.koinz.repository.PhotosRepo
import androidx.paging.Pager


class HomeViewModel(
    private val photosRepo: PhotosRepo,
    private val photosApi: PhotosApi, private val db: PhotosDataBase

) : ViewModel() {

    private val pagingSourceFactory = {db.photosDao().getAllPhotos()}


    @ExperimentalPagingApi
    val pager = Pager(
        PagingConfig(pageSize = 20),
        remoteMediator = PhotoDataSource(photosApi, 1, db),
        pagingSourceFactory =  pagingSourceFactory

    ).flow

//    @ExperimentalPagingApi
//    val pager = photosRepo.pager.
}
//    fun getPhotos(
//        method: String,
//        format: String,
//        nonJson: String,
//        text: String,
//        page: Int,
//        perPage: Int,
//        apiKey: String
//    ) = viewModelScope.launch {
//        withContext(Dispatchers.IO) {
//            photosRepo.getPhotos(
//                method,
//                format,
//                nonJson,
//                text,
//                page,
//                perPage,
//                apiKey
//            )
//        }
//
//    }



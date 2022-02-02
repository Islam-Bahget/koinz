package com.example.koinz.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koinz.model.PhotosResponse
import com.example.koinz.repository.PhotosRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val photosRepo: PhotosRepo) : ViewModel() {

    private val photosData = MutableLiveData<PhotosResponse>()
    val photosLiveData: LiveData<PhotosResponse>
        get() = photosData

    fun getPhotos(
        method: String,
        format: String,
        nonJson: String,
        text: String,
        page: Int,
        perPage: Int,
        apiKey: String
    ) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            photosRepo.getPhotos(
                method,
                format,
                nonJson,
                text,
                page,
                perPage,
                apiKey
            )
        }

    }

}
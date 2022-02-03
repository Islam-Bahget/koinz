package com.example.koinz.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.koinz.data.PhotosDataBase
import com.example.koinz.network.PhotosApi
import com.example.koinz.repository.PhotosRepo
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val repo: BaseRepo,
    private val photosApi: PhotosApi, private val db: PhotosDataBase
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
                repo as PhotosRepo,photosApi,db
            ) as T
            else -> throw IllegalArgumentException("Class Not Found")
        }
    }
}
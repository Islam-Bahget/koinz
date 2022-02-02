package com.example.koinz.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.koinz.repository.BaseRepo
import com.example.koinz.repository.PhotosRepo
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repo: BaseRepo) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repo as PhotosRepo) as T
            else -> throw IllegalArgumentException("Class Not Found")
        }
    }
}
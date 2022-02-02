package com.example.koinz.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.koinz.databinding.FragmentHomeBinding
import com.example.koinz.network.PhotosApi
import com.example.koinz.repository.PhotosRepo

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, PhotosRepo>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPhotos(
            "flickr.photos.search",
            "json",
            "50",
            "color",
            1,
            20,
            "d17378e37e555ebef55ab86c4180e8dc"
        )
        Log.d("islam", "onViewCreated: " + viewModel.photosLiveData.value)

    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepo() = PhotosRepo(retrofitClient.buildApi(PhotosApi::class.java))
}
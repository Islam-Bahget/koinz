package com.example.koinz.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.koinz.R
import com.example.koinz.adapters.PhotosAdapter
import com.example.koinz.adapters.PhotosClickListener
import com.example.koinz.data.PhotosDataBase
import com.example.koinz.databinding.FragmentHomeBinding
import com.example.koinz.network.PhotosApi
import com.example.koinz.repository.PhotosRepo
import com.google.android.material.badge.ExperimentalBadgeUtils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment :
    BaseFragment<HomeViewModel, FragmentHomeBinding, PhotosRepo, PhotosDataBase, PhotosApi>(),
    PhotosClickListener {


    private val photosAdapter = PhotosAdapter(this)

    @OptIn(ExperimentalPagingApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photosAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
                is LoadState.NotLoading -> {
                    binding.progress.visibility = View.GONE
                }
                is LoadState.Error -> {
                    Toast.makeText(requireContext(), "Try again", Toast.LENGTH_SHORT).show()
                    binding.progress.visibility = View.GONE
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pager.collectLatest {
                photosAdapter.submitData(it)

                //  Log.d("islam", "onViewCreated: " + photosAdapter.snapshot().items[0].title)

//                    adapter.submitData(it)

            }
        }

        binding.adapter = photosAdapter
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepo() =
        PhotosRepo(retrofitClient.buildApi(PhotosApi::class.java), getFragmentDb())

    override fun getFragmentApi() = retrofitClient.buildApi(PhotosApi::class.java)
    override fun getFragmentDb() =
        Room.databaseBuilder(requireContext(), PhotosDataBase::class.java, "photos.db").build()

    override fun onPhotoClicked(photoUrl: String) {

        findNavController().navigate(R.id.displayFragment, bundleOf("url" to photoUrl))
    }
}
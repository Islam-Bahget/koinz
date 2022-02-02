package com.example.koinz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.koinz.network.RetrofitClient
import com.example.koinz.repository.BaseRepo


/**
 *
 * this class will be the parent foe every fragment that need getting dta from repository and need a view model
 * */
abstract class BaseFragment<VM : ViewModel, VB : ViewBinding, R : BaseRepo> : Fragment() {

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM
    protected val retrofitClient = RetrofitClient()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        viewModel =
            ViewModelProvider(this, ViewModelFactory(getFragmentRepo()))[getViewModel()]
        binding = getFragmentBinding(inflater, container)
        return binding.root

    }

    abstract fun getViewModel(): Class<VM>
    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    abstract fun getFragmentRepo(): R

}
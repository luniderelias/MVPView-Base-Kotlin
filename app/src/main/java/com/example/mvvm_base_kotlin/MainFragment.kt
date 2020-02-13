package com.example.mvvm_base_kotlin

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_base_kotlin.base.application.App
import com.example.mvvm_base_kotlin.data.ExampleRepository
import com.example.mvvm_base_kotlin.data.ExampleRepositoryMock
import com.example.mvvm_base_kotlin.databinding.FragmentMainBinding

class MainFragment: Fragment() {


    private lateinit var viewModel: ExampleViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(@NonNull inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)

        this.activity?.application?.let {
            viewModel = createViewModel(it)
            viewModel.load()
            binding.viewModel = viewModel
            binding.lifecycleOwner = this
            this.lifecycle.addObserver(viewModel)
        }

        return binding.root
    }

    private fun createViewModel(application: Application): ExampleViewModel {
        val repository = ExampleRepositoryMock()

        val factory = ExampleViewModelFactory(repository, application)

        return ViewModelProvider(this, factory).get(ExampleViewModel::class.java)
    }
}
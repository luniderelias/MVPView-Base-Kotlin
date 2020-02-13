package com.example.mvpview_base_kotlin.main

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mvpview_base_kotlin.base.extensions.injectPresenter
import com.example.mvpview_base_kotlin.databinding.FragmentMainBinding
import com.example.mvpview_base_kotlin.viewmodel.ExampleViewModel
import com.example.mvpview_base_kotlin.viewmodel.ExampleViewModelFactory

class MainFragment: Fragment(), MainContract.View {

    override val presenter by injectPresenter(this)

    override lateinit var viewModel: ExampleViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(@NonNull inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)

        this.activity?.application?.let {
            viewModel = createViewModel(it)
            binding.viewModel = viewModel
            binding.lifecycleOwner = this
            initPresenter()
            this.lifecycle.addObserver(viewModel)
        }

        binding.appCompatImageView.setOnClickListener { onReload() }

        return binding.root
    }

    private fun createViewModel(application: Application): ExampleViewModel {
        val factory = ExampleViewModelFactory(application)

        return ViewModelProvider(this, factory).get(ExampleViewModel::class.java)
    }

    override fun initPresenter() {
        presenter.init(viewModel)
    }

    private fun onReload() {
        presenter.loadData()
    }
}
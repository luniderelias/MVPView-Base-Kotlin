package com.example.mvpview_base_kotlin.main

import com.example.mvpview_base_kotlin.data.ExampleRepositoryMock
import com.example.mvpview_base_kotlin.viewmodel.ExampleViewModel

class MainPresenter(override var view: MainContract.View,
                    private val repository: ExampleRepositoryMock) : MainContract.Presenter {

    override lateinit var exampleViewModel: ExampleViewModel

    override fun init(exampleViewModel: ExampleViewModel) {
        this.exampleViewModel = exampleViewModel
        loadData()
    }

    override fun loadData() {
        exampleViewModel.load()
        exampleViewModel.setExampleData(repository.getExample())
    }
}
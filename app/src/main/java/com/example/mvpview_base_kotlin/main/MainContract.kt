package com.example.mvpview_base_kotlin.main

import com.example.mvpview_base_kotlin.base.BasePresenter
import com.example.mvpview_base_kotlin.base.BaseView
import com.example.mvpview_base_kotlin.viewmodel.ExampleViewModel

interface MainContract {
    interface View : BaseView<Presenter> {
        var viewModel: ExampleViewModel
        fun initPresenter()
    }
    interface Presenter : BasePresenter<View> {
        var exampleViewModel: ExampleViewModel

        fun init(exampleViewModel: ExampleViewModel)
        fun loadData()
    }
}
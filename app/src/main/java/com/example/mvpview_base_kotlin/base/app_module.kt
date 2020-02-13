package com.example.mvpview_base_kotlin.base

import com.example.mvpview_base_kotlin.viewmodel.ExampleViewModel
import com.example.mvpview_base_kotlin.base.application.App
import com.example.mvpview_base_kotlin.base.application.ReleaseStartApplication
import com.example.mvpview_base_kotlin.base.application.StarterApplication
import com.example.mvpview_base_kotlin.data.ExampleRepository
import com.example.mvpview_base_kotlin.data.ExampleRepositoryMock
import com.example.mvpview_base_kotlin.main.MainContract
import com.example.mvpview_base_kotlin.main.MainPresenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Main
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    factory {
        ReleaseStartApplication()
    } bind StarterApplication::class

    factory {
        ExampleRepositoryMock()
    } bind ExampleRepository::class

    factory { (view: MainContract.View) ->
        MainPresenter(
            view = view,
            repository = get()
        )
    } bind MainContract.Presenter::class

    viewModel { ExampleViewModel(App()) }
}

val dispatcherModule = module {
    factory { Main as CoroutineDispatcher }
}
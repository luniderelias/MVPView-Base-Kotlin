package com.example.mvvm_base_kotlin.base

import com.example.mvvm_base_kotlin.ExampleViewModel
import com.example.mvvm_base_kotlin.base.application.App
import com.example.mvvm_base_kotlin.base.application.ReleaseStartApplication
import com.example.mvvm_base_kotlin.base.application.StarterApplication
import com.example.mvvm_base_kotlin.data.ExampleRepository
import com.example.mvvm_base_kotlin.data.ExampleRepositoryMock
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

    viewModel { ExampleViewModel(get("repository"), App())}
}

val dispatcherModule = module {
    factory { Main as CoroutineDispatcher }
}
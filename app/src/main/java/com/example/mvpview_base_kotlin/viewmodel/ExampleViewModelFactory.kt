package com.example.mvpview_base_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvpview_base_kotlin.data.ExampleRepositoryMock

class ExampleViewModelFactory constructor(
    private val application: Application
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(ExampleViewModel::class.java) ->
                    ExampleViewModel(application)
                else ->
                    throw IllegalArgumentException("Unknown Class.")
            }
        } as T
}
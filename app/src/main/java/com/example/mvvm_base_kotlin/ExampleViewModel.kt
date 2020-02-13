package com.example.mvvm_base_kotlin

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.example.mvvm_base_kotlin.base.extensions.shouldShowView
import com.example.mvvm_base_kotlin.data.ExampleData
import com.example.mvvm_base_kotlin.data.ExampleRepositoryMock

class ExampleViewModel(
    private val repository: ExampleRepositoryMock,
    application: Application
) : AndroidViewModel(application), LifecycleObserver {

    val loadingVisibility = MutableLiveData<Int>()
    val textVisibility = MutableLiveData<Int>()
    val onReloadVisibility = MutableLiveData<Int>()
    val message = MutableLiveData<String>()
    var response = MutableLiveData<ExampleData>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun load() {
        handleTextVisibility(false)

        response = repository.getExample()
        message.postValue(response.value?.message)
        handleTextVisibility(true)
    }

    private fun handleTextVisibility(visibility: Boolean) {
        textVisibility.postValue(visibility.shouldShowView)
        loadingVisibility.postValue((!visibility).shouldShowView)
    }

    fun onReload(view: View) {
        load()
    }

}
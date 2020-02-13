package com.example.mvpview_base_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.mvpview_base_kotlin.base.extensions.shouldShowView
import com.example.mvpview_base_kotlin.data.ExampleData

class ExampleViewModel(
    application: Application
) : AndroidViewModel(application), LifecycleObserver {

    val loadingVisibility = MutableLiveData<Int>()
    val textVisibility = MutableLiveData<Int>()
    val onReloadVisibility = MutableLiveData<Int>()
    val message = MutableLiveData<String>()
    var response = MutableLiveData<ExampleData>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun load() {}

    fun setExampleData(exampleData: ExampleData){
        handleTextVisibility(false)
        response.postValue(exampleData)
        message.postValue(response.value?.message)
        handleTextVisibility(true)
    }

    private fun handleTextVisibility(visibility: Boolean) {
        textVisibility.postValue(visibility.shouldShowView)
        loadingVisibility.postValue((!visibility).shouldShowView)
    }

}
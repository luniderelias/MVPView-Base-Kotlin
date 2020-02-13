package com.example.mvvm_base_kotlin.data

import androidx.lifecycle.MutableLiveData


interface ExampleRepository {
    fun getExample(): MutableLiveData<ExampleData>
}
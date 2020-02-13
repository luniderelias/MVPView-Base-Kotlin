package com.example.mvvm_base_kotlin.data

import androidx.lifecycle.MutableLiveData


class ExampleRepositoryMock : ExampleRepository {

    private var flag = false

    override fun getExample(): MutableLiveData<ExampleData> {
        flag = !flag
        return if (flag)
            MutableLiveData(ExampleData(message = "Hello World!"))
        else
            MutableLiveData(ExampleData(message = "Sorry, we could not find your message"))
    }

}
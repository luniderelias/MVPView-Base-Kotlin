package com.example.mvpview_base_kotlin.data

class ExampleRepositoryMock : ExampleRepository {

    private var flag = false

    override fun getExample(): ExampleData {
        flag = !flag
        return if (flag)
            ExampleData(message = "Hello World!")
        else
            ExampleData(message = "Sorry, we could not find your message")
    }

}
package com.example.mvpview_base_kotlin

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mvpview_base_kotlin.data.ExampleData
import com.example.mvpview_base_kotlin.data.ExampleRepositoryMock
import com.example.mvpview_base_kotlin.main.MainPresenter
import com.example.mvpview_base_kotlin.viewmodel.ExampleViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExampleViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val exampleViewModel =
        ExampleViewModel(mock())

    private val repository = ExampleRepositoryMock()

    private val mainPresenter =
        MainPresenter(mock(), repository)

    private var loadingVisibilityObserver = mock<Observer<Int>>()
    private var textVisibilityObserver = mock<Observer<Int>>()
    private var onReloadVisibilityObserver = mock<Observer<Int>>()
    private var messageObserver = mock<Observer<String>>()
    private var responseObserver = mock<Observer<ExampleData>>()

    @Before
    fun setup() {
        exampleViewModel.loadingVisibility.observeForever(loadingVisibilityObserver)
        exampleViewModel.textVisibility.observeForever(textVisibilityObserver)
        exampleViewModel.onReloadVisibility.observeForever(onReloadVisibilityObserver)
        exampleViewModel.message.observeForever(messageObserver)
        exampleViewModel.message.observeForever(messageObserver)
        exampleViewModel.response.observeForever(responseObserver)
    }

    @Test
    fun `test load success should show hello world`() {
        mainPresenter.init(exampleViewModel)

        verify(loadingVisibilityObserver).onChanged(VISIBLE)
        verify(loadingVisibilityObserver).onChanged(GONE)
        verify(textVisibilityObserver).onChanged(GONE)
        verify(textVisibilityObserver).onChanged(VISIBLE)
        verify(responseObserver).onChanged(exampleViewModel.response.value)
        verify(messageObserver).onChanged("Hello World!")

        assert(exampleViewModel.response.value!!.message == "Hello World!")
    }

    @Test
    fun `test load failed should show failed message`() {
        mainPresenter.init(exampleViewModel)
        mainPresenter.init(exampleViewModel)

        verify(loadingVisibilityObserver, times(2)).onChanged(VISIBLE)
        verify(loadingVisibilityObserver, times(2)).onChanged(GONE)
        verify(textVisibilityObserver, times(2)).onChanged(GONE)
        verify(textVisibilityObserver, times(2)).onChanged(VISIBLE)
        verify(responseObserver).onChanged(exampleViewModel.response.value)
        verify(messageObserver).onChanged("Sorry, we could not find your message")

        assert(exampleViewModel.response.value!!.message == "Sorry, we could not find your message")
    }
}
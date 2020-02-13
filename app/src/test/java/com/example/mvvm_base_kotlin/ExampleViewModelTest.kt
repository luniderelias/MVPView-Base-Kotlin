package com.example.mvvm_base_kotlin

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mvvm_base_kotlin.data.ExampleData
import com.example.mvvm_base_kotlin.data.ExampleRepositoryMock
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

    private val repository = ExampleRepositoryMock()
    private val exampleViewModel = ExampleViewModel(repository, mock())

    private var loadingVisibilityObserver = mock<Observer<Int>>()
    private var textVisibilityObserver = mock<Observer<Int>>()
    private var onReloadVisibilityObserver = mock<Observer<Int>>()
    private var messageObserver = mock<Observer<String>>()

    @Before
    fun setup() {
        exampleViewModel.loadingVisibility.observeForever(loadingVisibilityObserver)
        exampleViewModel.textVisibility.observeForever(textVisibilityObserver)
        exampleViewModel.onReloadVisibility.observeForever(onReloadVisibilityObserver)
        exampleViewModel.message.observeForever(messageObserver)
    }

    @Test
    fun `test load success should show hello world`() {
        exampleViewModel.load()
        verify(loadingVisibilityObserver).onChanged(VISIBLE)
        verify(loadingVisibilityObserver).onChanged(GONE)
        verify(textVisibilityObserver).onChanged(GONE)
        verify(textVisibilityObserver).onChanged(VISIBLE)
        verify(messageObserver).onChanged("Hello World!")
    }

    @Test
    fun `test load failed should show failed message`() {
        exampleViewModel.load()
        exampleViewModel.load()
        verify(loadingVisibilityObserver, times(2)).onChanged(VISIBLE)
        verify(loadingVisibilityObserver, times(2)).onChanged(GONE)
        verify(textVisibilityObserver, times(2)).onChanged(GONE)
        verify(textVisibilityObserver, times(2)).onChanged(VISIBLE)
        verify(messageObserver).onChanged("Sorry, we could not find your message")
    }
}
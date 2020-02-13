package com.example.mvpview_base_kotlin.base

interface BaseView<out T : BasePresenter<*>> {
    val presenter: T
}
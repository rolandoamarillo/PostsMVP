package com.rolandoamarillo.demo.posts

interface BasePresenter<T> {

    /**
     */
    fun subscribe(view: T)

    /**
     */
    fun unsubscribe()

}
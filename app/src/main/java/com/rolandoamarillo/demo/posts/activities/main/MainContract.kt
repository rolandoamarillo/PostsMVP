package com.rolandoamarillo.demo.posts.activities.main

import com.rolandoamarillo.demo.posts.BasePresenter
import com.rolandoamarillo.demo.posts.BaseView

interface MainContract {

    interface MainView : BaseView<MainPresenter> {
        fun onPostsCleared()

        fun onPostsReloaded()

        fun resetView()
    }

    interface MainPresenter : BasePresenter<MainView> {
        fun clearPosts()

        fun reloadPosts()

        fun resetView()
    }
}
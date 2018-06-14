package com.rolandoamarillo.demo.posts.fragments.posts.all

import com.rolandoamarillo.demo.posts.BasePresenter
import com.rolandoamarillo.demo.posts.BaseView
import com.rolandoamarillo.demo.posts.model.Post

interface AllPostListContract {

    interface AllPostListView : BaseView<AllPostListPresenter> {

        fun showProgress()

        fun onPostsLoaded(posts: List<Post>)

        fun onPostsError(throwable: Throwable)

    }

    interface AllPostListPresenter : BasePresenter<AllPostListView> {
        fun getPosts()
    }

}
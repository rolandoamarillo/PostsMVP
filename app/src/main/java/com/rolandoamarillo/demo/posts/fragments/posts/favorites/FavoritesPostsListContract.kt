package com.rolandoamarillo.demo.posts.fragments.posts.favorites

import com.rolandoamarillo.demo.posts.BasePresenter
import com.rolandoamarillo.demo.posts.BaseView
import com.rolandoamarillo.demo.posts.model.Post

interface FavoritesPostsListContract {

    interface FavoritesPostListView : BaseView<FavoritesListPresenter> {

        fun showProgress()

        fun onPostsLoaded(posts: List<Post>)

        fun onPostsError(throwable: Throwable)

    }

    interface FavoritesListPresenter : BasePresenter<FavoritesPostListView> {
        fun getFavoritesPosts()
    }

}
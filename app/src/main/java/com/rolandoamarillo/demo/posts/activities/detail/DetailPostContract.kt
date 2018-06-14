package com.rolandoamarillo.demo.posts.activities.detail

import com.rolandoamarillo.demo.posts.BasePresenter
import com.rolandoamarillo.demo.posts.BaseView

interface DetailPostContract {

    interface DetailPostView : BaseView<DetailPostPresenter> {

        fun onUserRetrieved()

        fun onUserError(throwable: Throwable)

        fun onCommentsRetrieved()

        fun onCommentsError(throwable: Throwable)
    }

    interface DetailPostPresenter : BasePresenter<DetailPostView> {

        fun getUser()

        fun getComments()

        fun toggleFavorite()

    }
}
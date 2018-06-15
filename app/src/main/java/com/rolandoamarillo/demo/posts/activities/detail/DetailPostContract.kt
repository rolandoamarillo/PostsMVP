package com.rolandoamarillo.demo.posts.activities.detail

import com.rolandoamarillo.demo.posts.BasePresenter
import com.rolandoamarillo.demo.posts.BaseView
import com.rolandoamarillo.demo.posts.model.Comment
import com.rolandoamarillo.demo.posts.model.Post
import com.rolandoamarillo.demo.posts.model.User

interface DetailPostContract {

    interface DetailPostView : BaseView<DetailPostPresenter> {

        fun onUserRetrieved(user: User)

        fun onUserError(throwable: Throwable)

        fun onCommentsRetrieved(comments: List<Comment>)

        fun onCommentsError(throwable: Throwable)
    }

    interface DetailPostPresenter : BasePresenter<DetailPostView> {

        fun getUser(userId: Int)

        fun getComments(postId: Int)

        fun toggleFavorite(post: Post)

        fun readPost(post: Post)

    }
}
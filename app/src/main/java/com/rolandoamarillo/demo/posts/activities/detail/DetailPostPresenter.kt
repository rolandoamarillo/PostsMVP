package com.rolandoamarillo.demo.posts.activities.detail

import com.rolandoamarillo.demo.posts.repository.PostsLocalDataSource
import com.rolandoamarillo.demo.posts.repository.PostsRemoteDataSource
import io.reactivex.disposables.CompositeDisposable

class DetailPostPresenter(private val postsRemoteDataSource: PostsRemoteDataSource, private val postsLocalDataSource: PostsLocalDataSource) : DetailPostContract.DetailPostPresenter {

    private val compositeDisposable = CompositeDisposable()

    private var view: DetailPostContract.DetailPostView? = null

    override fun subscribe(view: DetailPostContract.DetailPostView) {
        this.view = view
    }

    override fun unsubscribe() {
        this.view = null
    }

    override fun getUser() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getComments() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toggleFavorite() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
package com.rolandoamarillo.demo.posts.activities.main

import com.rolandoamarillo.demo.posts.repository.PostsLocalDataSource
import com.rolandoamarillo.demo.posts.repository.PostsRemoteDataSource
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(private val postsRemoteDataSource: PostsRemoteDataSource,
                    private val postsLocalDataSource: PostsLocalDataSource) : MainContract.MainPresenter {

    private var compositeDisposable: CompositeDisposable? = null

    private var view: MainContract.MainView? = null

    override fun subscribe(view: MainContract.MainView) {
        this.view = view
        compositeDisposable = CompositeDisposable()
    }

    override fun unsubscribe() {
        this.view = null
        compositeDisposable?.let {
            if (!compositeDisposable!!.isDisposed) {
                compositeDisposable?.dispose()
            }
            compositeDisposable = null
        }
    }

    override fun clearPosts() {
        postsLocalDataSource.clearPosts()
        view?.onPostsCleared()
    }

    override fun reloadPosts() {
        val disposable = postsRemoteDataSource.getPosts().subscribe({
            postsLocalDataSource.savePosts(it.toMutableList())
            view?.onPostsReloaded()
        }, {
            it.printStackTrace()
            view?.onPostsReloaded()
        })
        compositeDisposable!!.add(disposable)
    }

    override fun resetView() {
        view?.resetView()
    }
}
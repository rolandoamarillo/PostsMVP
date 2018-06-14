package com.rolandoamarillo.demo.posts.fragments.posts.all

import com.rolandoamarillo.demo.posts.repository.PostsLocalDataSource
import com.rolandoamarillo.demo.posts.repository.PostsRemoteDataSource
import io.reactivex.disposables.CompositeDisposable

class AllPostsListPresenter(private val postsRemoteDataSource: PostsRemoteDataSource,
                            private val postsLocalDataSource: PostsLocalDataSource) : AllPostListContract.AllPostListPresenter {

    private val compositeDisposable = CompositeDisposable()

    var view: AllPostListContract.AllPostListView? = null

    override fun subscribe(view: AllPostListContract.AllPostListView) {
        this.view = view
        getPosts()
    }

    override fun unsubscribe() {
        this.view = null
    }

    override fun getPosts() {
        val observable = if (!postsLocalDataSource.needsRefresh()) {
            postsLocalDataSource.getPosts()
        } else {
            postsRemoteDataSource.getPosts()
        }

        val disposable = observable.subscribe({
            postsLocalDataSource.savePosts(it.toMutableList())
            view?.onPostsLoaded(it)
        }, {
            it.printStackTrace()
            view?.onPostsError(it)
        })
        compositeDisposable.add(disposable)
    }

}
package com.rolandoamarillo.demo.posts.fragments.posts.all

import com.rolandoamarillo.demo.posts.activities.main.MainContract
import com.rolandoamarillo.demo.posts.model.Post
import com.rolandoamarillo.demo.posts.repository.PostsLocalDataSource
import com.rolandoamarillo.demo.posts.repository.PostsRemoteDataSource
import io.reactivex.disposables.CompositeDisposable

class AllPostsListPresenter(private val postsRemoteDataSource: PostsRemoteDataSource,
                            private val postsLocalDataSource: PostsLocalDataSource, private val mainPresenter: MainContract.MainPresenter) : AllPostListContract.AllPostListPresenter {

    private var compositeDisposable: CompositeDisposable? = null

    var view: AllPostListContract.AllPostListView? = null

    override fun subscribe(view: AllPostListContract.AllPostListView) {
        this.view = view
        compositeDisposable = CompositeDisposable()
        getPosts()
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
        compositeDisposable!!.add(disposable)
    }

    override fun removePost(id: Int) {
        postsLocalDataSource.deletePost(id)
        mainPresenter.resetView()
    }
}
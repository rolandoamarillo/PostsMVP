package com.rolandoamarillo.demo.posts.activities.detail

import com.rolandoamarillo.demo.posts.model.Post
import com.rolandoamarillo.demo.posts.repository.PostsLocalDataSource
import com.rolandoamarillo.demo.posts.repository.PostsRemoteDataSource
import com.rolandoamarillo.demo.posts.repository.UserRemoteDataSource
import io.reactivex.disposables.CompositeDisposable

class DetailPostPresenter(private val userRemoteDataSource: UserRemoteDataSource, private val postsLocalDataSource: PostsLocalDataSource) :
        DetailPostContract.DetailPostPresenter {

    private val compositeDisposable = CompositeDisposable()

    private var view: DetailPostContract.DetailPostView? = null

    override fun subscribe(view: DetailPostContract.DetailPostView) {
        this.view = view
    }

    override fun unsubscribe() {
        this.view = null
    }

    override fun getUser(userId: Int) {
        val disposable = userRemoteDataSource.getUser(userId)
                .subscribe({ view?.onUserRetrieved(it) }, { view?.onUserError(it) })
        compositeDisposable.clear()
        compositeDisposable.add(disposable)
    }

    override fun getComments() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toggleFavorite(post: Post) {
        post.favorite = !post.favorite
        postsLocalDataSource.savePost(post)
    }

    override fun readPost(post: Post) {
        post.notRead = false
        postsLocalDataSource.savePost(post)
    }
}
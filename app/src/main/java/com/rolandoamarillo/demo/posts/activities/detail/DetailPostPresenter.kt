package com.rolandoamarillo.demo.posts.activities.detail

import com.rolandoamarillo.demo.posts.model.Post
import com.rolandoamarillo.demo.posts.repository.CommentsRemoteDataSource
import com.rolandoamarillo.demo.posts.repository.PostsLocalDataSource
import com.rolandoamarillo.demo.posts.repository.UserRemoteDataSource
import io.reactivex.disposables.CompositeDisposable

class DetailPostPresenter(private val userRemoteDataSource: UserRemoteDataSource,
                          private val postsLocalDataSource: PostsLocalDataSource,
                          private val commentsRemoteDataSource: CommentsRemoteDataSource) :
        DetailPostContract.DetailPostPresenter {

    private var compositeDisposable: CompositeDisposable? = null

    private var view: DetailPostContract.DetailPostView? = null

    override fun subscribe(view: DetailPostContract.DetailPostView) {
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

    override fun getUser(userId: Int) {
        val disposable = userRemoteDataSource.getUser(userId)
                .subscribe({ view?.onUserRetrieved(it) }, { view?.onUserError(it) })
        compositeDisposable!!.add(disposable)
    }

    override fun getComments(postId: Int) {
        val disposable = commentsRemoteDataSource.getComments(postId)
                .subscribe({ view?.onCommentsRetrieved(it) }, { view?.onCommentsError(it) })
        compositeDisposable!!.add(disposable)
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
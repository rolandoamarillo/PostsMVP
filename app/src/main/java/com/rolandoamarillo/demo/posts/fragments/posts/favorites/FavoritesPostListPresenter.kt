package com.rolandoamarillo.demo.posts.fragments.posts.favorites

import com.rolandoamarillo.demo.posts.model.Post
import com.rolandoamarillo.demo.posts.repository.PostsLocalDataSource
import com.rolandoamarillo.demo.posts.repository.PostsRemoteDataSource
import io.reactivex.disposables.CompositeDisposable

class FavoritesPostListPresenter(private val postsRemoteDataSource: PostsRemoteDataSource,
                                 private val postsLocalDataSource: PostsLocalDataSource) : FavoritesPostsListContract.FavoritesListPresenter {

    private val compositeDisposable = CompositeDisposable()

    var view: FavoritesPostsListContract.FavoritesPostListView? = null

    override fun subscribe(view: FavoritesPostsListContract.FavoritesPostListView) {
        this.view = view
        getFavoritesPosts()
    }

    override fun unsubscribe() {
        this.view = null
    }

    override fun getFavoritesPosts() {
        val observable = if (!postsLocalDataSource.needsRefresh()) {
            postsLocalDataSource.getPosts()
        } else {
            postsRemoteDataSource.getPosts()
        }

        val disposable = observable.map({
            postsLocalDataSource.savePosts(it.toMutableList())
            val posts = ArrayList<Post>()
            for (post in it) {
                if (post.favorite) {
                    posts.add(post)
                }
            }
            posts
        }).subscribe({
            view?.onPostsLoaded(it)
        }, {
            it.printStackTrace()
            view?.onPostsError(it)
        })
        compositeDisposable.add(disposable)
    }

}
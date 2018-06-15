package com.rolandoamarillo.demo.posts.repository

import com.rolandoamarillo.demo.posts.api.CommentApi
import com.rolandoamarillo.demo.posts.model.Comment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CommentsRemoteRepository(private val commentApi: CommentApi) : CommentsRemoteDataSource {

    override fun getComments(postId: Int): Observable<List<Comment>> {
        return commentApi.getComments(postId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

}
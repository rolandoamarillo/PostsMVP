package com.rolandoamarillo.demo.posts.repository

import com.rolandoamarillo.demo.posts.model.Comment
import io.reactivex.Observable

interface CommentsRemoteDataSource {

    fun getComments(postId: Int): Observable<List<Comment>>

}
package com.rolandoamarillo.demo.posts.api

import com.rolandoamarillo.demo.posts.model.Comment
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentApi {

    @GET("comments")
    fun getComments(@Query("postId") postId: Int): Observable<List<Comment>>

}
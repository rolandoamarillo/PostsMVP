package com.rolandoamarillo.demo.posts.api

import com.rolandoamarillo.demo.posts.model.Post
import com.rolandoamarillo.demo.posts.responses.PostResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface PostsApi {

    @GET("posts")
    fun getPosts(): Observable<List<PostResponse>>

}
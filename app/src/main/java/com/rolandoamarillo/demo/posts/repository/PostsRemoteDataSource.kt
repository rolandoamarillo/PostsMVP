package com.rolandoamarillo.demo.posts.repository

import com.rolandoamarillo.demo.posts.model.Post
import io.reactivex.Observable

interface PostsRemoteDataSource {

    fun getPosts(): Observable<List<Post>>

}
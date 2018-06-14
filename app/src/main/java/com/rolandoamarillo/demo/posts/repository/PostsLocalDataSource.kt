package com.rolandoamarillo.demo.posts.repository

import com.rolandoamarillo.demo.posts.model.Post
import io.reactivex.Observable

interface PostsLocalDataSource {

    fun getPosts(): Observable<MutableList<Post>>

    fun savePosts(posts: MutableList<Post>)

    fun needsRefresh(): Boolean

    fun clearPosts()

    fun savePost(post: Post)

}
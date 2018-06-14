package com.rolandoamarillo.demo.posts.repository

import com.rolandoamarillo.demo.posts.api.PostsApi
import com.rolandoamarillo.demo.posts.model.Post
import com.rolandoamarillo.demo.posts.responses.PostResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostsRemoteRepository(private val postsApi: PostsApi) : PostsRemoteDataSource {

    override fun getPosts(): Observable<List<Post>> {
        return postsApi.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map({ postsResponse: List<PostResponse> ->
                    val posts = ArrayList<Post>()
                    for ((index, postResponse) in postsResponse.withIndex()) {
                        val post = Post.parseResponse(postResponse)
                        post.notRead = index < 20
                        posts.add(post)
                    }
                    posts
                })
    }
}
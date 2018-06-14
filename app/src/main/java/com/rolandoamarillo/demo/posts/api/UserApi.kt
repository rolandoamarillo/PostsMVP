package com.rolandoamarillo.demo.posts.api

import com.rolandoamarillo.demo.posts.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: Int): Observable<User>

}
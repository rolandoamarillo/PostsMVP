package com.rolandoamarillo.demo.posts.repository

import com.rolandoamarillo.demo.posts.model.User
import io.reactivex.Observable

interface UserRemoteDataSource {

    fun getUser(userId: Int): Observable<User>

}
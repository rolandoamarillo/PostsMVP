package com.rolandoamarillo.demo.posts.repository

import com.rolandoamarillo.demo.posts.api.UserApi
import com.rolandoamarillo.demo.posts.model.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRemoteRepository(private val userApi: UserApi) : UserRemoteDataSource {

    override fun getUser(userId: Int): Observable<User> {
        return userApi.getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
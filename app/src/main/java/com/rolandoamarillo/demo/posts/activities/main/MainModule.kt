package com.rolandoamarillo.demo.posts.activities.main

import com.rolandoamarillo.demo.posts.di.ActivityScoped
import com.rolandoamarillo.demo.posts.repository.PostsLocalDataSource
import com.rolandoamarillo.demo.posts.repository.PostsRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @ActivityScoped
    @Provides
    fun provideMainPresenter(postsRemoteDataSource: PostsRemoteDataSource, postsLocalDataSource: PostsLocalDataSource): MainContract.MainPresenter {
        return MainPresenter(postsRemoteDataSource, postsLocalDataSource)
    }

}
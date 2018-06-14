package com.rolandoamarillo.demo.posts.activities.detail

import com.rolandoamarillo.demo.posts.di.ActivityScoped
import com.rolandoamarillo.demo.posts.repository.PostsLocalDataSource
import com.rolandoamarillo.demo.posts.repository.PostsRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DetailPostModule {

    @ActivityScoped
    @Provides
    fun provideDetailPostPresenter(postsRemoteDataSource: PostsRemoteDataSource, postsLocalDataSource: PostsLocalDataSource): DetailPostContract.DetailPostPresenter {
        return DetailPostPresenter(postsRemoteDataSource, postsLocalDataSource)
    }

}
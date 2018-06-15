package com.rolandoamarillo.demo.posts.activities.detail

import com.rolandoamarillo.demo.posts.di.ActivityScoped
import com.rolandoamarillo.demo.posts.repository.CommentsRemoteDataSource
import com.rolandoamarillo.demo.posts.repository.PostsLocalDataSource
import com.rolandoamarillo.demo.posts.repository.PostsRemoteDataSource
import com.rolandoamarillo.demo.posts.repository.UserRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DetailPostModule {

    @ActivityScoped
    @Provides
    fun provideDetailPostPresenter(userRemoteDataSource: UserRemoteDataSource, postsLocalDataSource: PostsLocalDataSource, commentsRemoteDataSource: CommentsRemoteDataSource): DetailPostContract.DetailPostPresenter {
        return DetailPostPresenter(userRemoteDataSource, postsLocalDataSource, commentsRemoteDataSource)
    }

}
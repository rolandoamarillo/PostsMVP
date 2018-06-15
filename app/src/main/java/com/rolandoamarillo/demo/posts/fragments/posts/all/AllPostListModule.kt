package com.rolandoamarillo.demo.posts.fragments.posts.all

import com.rolandoamarillo.demo.posts.activities.main.MainContract
import com.rolandoamarillo.demo.posts.di.FragmentScoped
import com.rolandoamarillo.demo.posts.repository.PostsLocalDataSource
import com.rolandoamarillo.demo.posts.repository.PostsRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class AllPostListModule {

    @FragmentScoped
    @Provides
    fun provideAllPostsListPresenter(postsRemoteDataSource: PostsRemoteDataSource, postsLocalDataSource: PostsLocalDataSource, mainPresenter: MainContract.MainPresenter): AllPostListContract.AllPostListPresenter {
        return AllPostsListPresenter(postsRemoteDataSource, postsLocalDataSource, mainPresenter)
    }

}
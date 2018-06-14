package com.rolandoamarillo.demo.posts.fragments.posts.favorites

import com.rolandoamarillo.demo.posts.di.FragmentScoped
import com.rolandoamarillo.demo.posts.repository.PostsLocalDataSource
import com.rolandoamarillo.demo.posts.repository.PostsRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class FavoritesPostListModule {

    @FragmentScoped
    @Provides
    fun provideAllPostsListPresenter(postsRemoteDataSource: PostsRemoteDataSource, postsLocalDataSource: PostsLocalDataSource): FavoritesPostsListContract.FavoritesListPresenter {
        return FavoritesPostListPresenter(postsRemoteDataSource, postsLocalDataSource)
    }

}
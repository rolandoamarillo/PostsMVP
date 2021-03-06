package com.rolandoamarillo.demo.posts

import android.content.Context
import android.support.annotation.NonNull
import com.rolandoamarillo.demo.posts.api.CommentApi
import com.rolandoamarillo.demo.posts.api.PostsApi
import com.rolandoamarillo.demo.posts.api.UserApi
import com.rolandoamarillo.demo.posts.repository.*
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Rolando on 10/06/2018
 * Dagger module for the application
 */
@Module
class ApplicationModule(val context: Context) {

    @Singleton
    @Provides
    @NonNull
    fun provideContext(): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun providePostsApi(retrofit: Retrofit): PostsApi {
        return retrofit.create(PostsApi::class.java)
    }

    @Singleton
    @Provides
    fun providePostsRemoteDataSource(postsApi: PostsApi): PostsRemoteDataSource {
        return PostsRemoteRepository(postsApi)
    }

    @Singleton
    @Provides
    fun providePostsLocalDataSource(): PostsLocalDataSource {
        return PostsLocalRepository()
    }

    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(userApi: UserApi): UserRemoteDataSource {
        return UserRemoteRepository(userApi)
    }

    @Singleton
    @Provides
    fun provideCommentApi(retrofit: Retrofit): CommentApi {
        return retrofit.create(CommentApi::class.java)
    }

    @Singleton
    @Provides
    fun proviceCommentRemoteDataSource(commentApi: CommentApi): CommentsRemoteDataSource {
        return CommentsRemoteRepository(commentApi)
    }

}
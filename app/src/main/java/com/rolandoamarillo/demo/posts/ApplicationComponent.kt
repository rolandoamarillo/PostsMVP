package com.rolandoamarillo.demo.posts

import com.rolandoamarillo.demo.posts.activities.detail.DetailPostActivityModuleInjector
import com.rolandoamarillo.demo.posts.activities.main.MainActivityModuleInjector
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Rolando on 10/06/2018
 * Dagger component for the application
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class, MainActivityModuleInjector::class, DetailPostActivityModuleInjector::class])
interface ApplicationComponent {

    /**
     * Injects the PostsApplication
     */
    fun inject(postsApplication: PostsApplication)

}
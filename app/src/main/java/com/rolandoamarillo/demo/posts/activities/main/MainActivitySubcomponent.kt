package com.rolandoamarillo.demo.posts.activities.main

import com.rolandoamarillo.demo.posts.di.ActivityScoped
import com.rolandoamarillo.demo.posts.fragments.posts.all.AllPostListFragmentModuleInjector
import com.rolandoamarillo.demo.posts.fragments.posts.favorites.FavoritesPostListFragmentModuleInjector
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScoped
@Subcomponent(modules = [MainModule::class, AllPostListFragmentModuleInjector::class, FavoritesPostListFragmentModuleInjector::class])
interface MainActivitySubcomponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()

}
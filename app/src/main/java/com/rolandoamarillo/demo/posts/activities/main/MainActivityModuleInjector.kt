package com.rolandoamarillo.demo.posts.activities.main

import dagger.Module
import android.app.Activity
import com.rolandoamarillo.demo.posts.fragments.posts.all.AllPostListFragmentSubcomponent
import com.rolandoamarillo.demo.posts.fragments.posts.favorites.FavoritesPostListFragmentSubcomponent
import dagger.android.AndroidInjector
import dagger.android.ActivityKey
import dagger.multibindings.IntoMap
import dagger.Binds


@Module(subcomponents = [MainActivitySubcomponent::class, AllPostListFragmentSubcomponent::class, FavoritesPostListFragmentSubcomponent::class])
abstract class MainActivityModuleInjector {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun bindMainActivityInjectorFactory(builder: MainActivitySubcomponent.Builder): AndroidInjector.Factory<out Activity>

}
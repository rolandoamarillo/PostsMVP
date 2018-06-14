package com.rolandoamarillo.demo.posts.fragments.posts.favorites

import dagger.Module
import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import dagger.Binds
import dagger.android.support.FragmentKey


@Module(subcomponents = [FavoritesPostListFragmentSubcomponent::class])
abstract class FavoritesPostListFragmentModuleInjector {

    @Binds
    @IntoMap
    @FragmentKey(FavoritesPostListFragment::class)
    internal abstract fun bindAllPostListFragmentInjectorFactory(builder: FavoritesPostListFragmentSubcomponent.Builder): AndroidInjector.Factory<out Fragment>

}
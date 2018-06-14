package com.rolandoamarillo.demo.posts.fragments.posts.favorites

import com.rolandoamarillo.demo.posts.di.FragmentScoped
import dagger.Subcomponent
import dagger.android.AndroidInjector

@FragmentScoped
@Subcomponent(modules = [FavoritesPostListModule::class])
interface FavoritesPostListFragmentSubcomponent : AndroidInjector<FavoritesPostListFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<FavoritesPostListFragment>()

}
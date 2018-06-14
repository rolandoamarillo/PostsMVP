package com.rolandoamarillo.demo.posts.fragments.posts.all

import com.rolandoamarillo.demo.posts.di.FragmentScoped
import dagger.Subcomponent
import dagger.android.AndroidInjector

@FragmentScoped
@Subcomponent(modules = [AllPostListModule::class])
interface AllPostListFragmentSubcomponent : AndroidInjector<AllPostListFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<AllPostListFragment>()

}
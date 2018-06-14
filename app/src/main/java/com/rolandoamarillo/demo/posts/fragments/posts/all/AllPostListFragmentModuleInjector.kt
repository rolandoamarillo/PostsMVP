package com.rolandoamarillo.demo.posts.fragments.posts.all

import dagger.Module
import android.support.v4.app.Fragment
import com.rolandoamarillo.demo.posts.di.FragmentScoped
import com.rolandoamarillo.demo.posts.repository.PostsRemoteDataSource
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import dagger.Binds
import dagger.android.support.FragmentKey


@Module(subcomponents = [AllPostListFragmentSubcomponent::class])
abstract class AllPostListFragmentModuleInjector {

    @Binds
    @IntoMap
    @FragmentKey(AllPostListFragment::class)
    internal abstract fun bindAllPostListFragmentInjectorFactory(builder: AllPostListFragmentSubcomponent.Builder): AndroidInjector.Factory<out Fragment>


}
package com.rolandoamarillo.demo.posts.activities.detail

import dagger.Module
import android.app.Activity
import com.rolandoamarillo.demo.posts.fragments.posts.all.AllPostListFragmentSubcomponent
import com.rolandoamarillo.demo.posts.fragments.posts.favorites.FavoritesPostListFragmentSubcomponent
import dagger.android.AndroidInjector
import dagger.android.ActivityKey
import dagger.multibindings.IntoMap
import dagger.Binds


@Module(subcomponents = [DetailPostActivitySubcomponent::class])
abstract class DetailPostActivityModuleInjector {

    @Binds
    @IntoMap
    @ActivityKey(DetailPostActivity::class)
    internal abstract fun bindDetailPostActivityInjectorFactory(builder: DetailPostActivitySubcomponent.Builder): AndroidInjector.Factory<out Activity>

}
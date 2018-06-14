package com.rolandoamarillo.demo.posts.activities.detail

import com.rolandoamarillo.demo.posts.di.ActivityScoped
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScoped
@Subcomponent(modules = [DetailPostModule::class])
interface DetailPostActivitySubcomponent : AndroidInjector<DetailPostActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DetailPostActivity>()

}
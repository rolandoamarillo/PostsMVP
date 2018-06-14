package com.rolandoamarillo.demo.posts

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.DispatchingAndroidInjector
import io.realm.Realm
import javax.inject.Inject

/**
 * Created by Rolando on 10/06/2018
 * Application class for the posts, it is the starting point on the application
 */
class PostsApplication : Application(), HasActivityInjector {

    /**
     * Android injector for the activities and fragments
     */
    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
                .inject(this)
        Realm.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }


}
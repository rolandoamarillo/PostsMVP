package com.rolandoamarillo.demo.posts.activities.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.rolandoamarillo.demo.posts.fragments.posts.favorites.FavoritesPostListFragment
import com.rolandoamarillo.demo.posts.fragments.posts.all.AllPostListFragment

/**
 * Created by Rolando on 11/06/2018
 */
class SectionsPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            AllPostListFragment.newInstance()
        } else {
            FavoritesPostListFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
}
package com.rolandoamarillo.demo.posts.repository

import com.rolandoamarillo.demo.posts.model.Post
import io.reactivex.Observable
import io.realm.Realm


class PostsLocalRepository : PostsLocalDataSource {

    var posts: MutableList<Post>? = null

    override fun getPosts(): Observable<MutableList<Post>> {
        posts?.let {
            return Observable.just(it)
        }
        val realm = Realm.getDefaultInstance()
        val query = realm.where(Post::class.java)
        val results = query.findAll()
        posts = results
        return Observable.just(results)
    }

    override fun savePosts(posts: MutableList<Post>) {
        if (this.posts != posts) {
            val realm = Realm.getDefaultInstance()
            realm.executeTransaction({ realm.insertOrUpdate(posts) })
            this.posts = posts
        }
    }

    override fun clearPosts() {
        var realm = Realm.getDefaultInstance()
        realm.executeTransaction({ realm.delete(Post::class.java) })
        realm = Realm.getDefaultInstance()
        val query = realm.where(Post::class.java)
        val results = query.findAll()
        posts = results
    }

    override fun needsRefresh(): Boolean {
        posts?.let {
            val realm = Realm.getDefaultInstance()
            val query = realm.where(Post::class.java)
            val results = query.findAll()
            if (results.isNotEmpty()) {
                posts = results
            }
        }
        return posts == null
    }

    override fun savePost(post: Post) {
        var realm = Realm.getDefaultInstance()
        realm.executeTransaction({ realm.insertOrUpdate(post) })
        realm = Realm.getDefaultInstance()
        val query = realm.where(Post::class.java)
        val results = query.findAll()
        posts = results
    }
}
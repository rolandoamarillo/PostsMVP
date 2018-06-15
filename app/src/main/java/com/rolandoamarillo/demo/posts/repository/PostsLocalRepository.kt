package com.rolandoamarillo.demo.posts.repository

import com.rolandoamarillo.demo.posts.model.Post
import io.reactivex.Observable
import io.realm.Realm
import io.realm.Sort


class PostsLocalRepository : PostsLocalDataSource {

    var posts: MutableList<Post>? = null

    override fun getPosts(): Observable<MutableList<Post>> {
        posts?.let {
            return Observable.just(it)
        }
        val realm = Realm.getDefaultInstance()
        val query = realm.where(Post::class.java)
        val results = query.findAll().sort("id", Sort.ASCENDING)
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
        val results = query.findAll().sort("id", Sort.ASCENDING)
        posts = results
    }

    override fun needsRefresh(): Boolean {
        posts?.let {
            val realm = Realm.getDefaultInstance()
            val query = realm.where(Post::class.java)
            val results = query.findAll().sort("id", Sort.ASCENDING)
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
        val results = query.findAll().sort("id", Sort.ASCENDING)
        posts = results
    }

    override fun deletePost(id: Int) {
        var realm = Realm.getDefaultInstance()
        realm.executeTransaction({
            val result = realm.where(Post::class.java).equalTo("id", id).findAll().sort("id", Sort.ASCENDING)
            if (result.isNotEmpty()) {
                result.deleteAllFromRealm()
            }
        })
        realm = Realm.getDefaultInstance()
        val query = realm.where(Post::class.java)
        val results = query.findAll().sort("id", Sort.ASCENDING)
        posts = results
    }
}
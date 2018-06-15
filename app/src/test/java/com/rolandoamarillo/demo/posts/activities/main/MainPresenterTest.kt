package com.rolandoamarillo.demo.posts.activities.main

import com.rolandoamarillo.demo.posts.model.Post
import com.rolandoamarillo.demo.posts.repository.PostsLocalDataSource
import com.rolandoamarillo.demo.posts.repository.PostsRemoteDataSource
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

class MainPresenterTest {

    lateinit var presenter: MainPresenter

    lateinit var postsLocalDataSource: PostsLocalDataSource

    lateinit var postsRemoteDataSource: PostsRemoteDataSource

    lateinit var view: MainContract.MainView

    @Before
    fun setup() {
        postsLocalDataSource = Mockito.mock(PostsLocalDataSource::class.java)
        postsRemoteDataSource = Mockito.mock(PostsRemoteDataSource::class.java)
        view = Mockito.mock(MainContract.MainView::class.java)

        presenter = MainPresenter(postsRemoteDataSource, postsLocalDataSource)
        presenter.subscribe(view)
    }

    @After
    fun turndown() {
        presenter.unsubscribe()
    }

    @Test
    fun clearPostsTest() {
        presenter.clearPosts()
        verify(postsLocalDataSource).clearPosts()
        verify(view).onPostsCleared()
    }

    @Test
    fun resetViewTest() {
        presenter.resetView()
        verify(view).resetView()
    }

    @Test
    fun reloadPostsTest() {
        val posts = mutableListOf<Post>()
        `when`(postsRemoteDataSource.getPosts()).thenReturn(Observable.just(posts))
        presenter.reloadPosts()
        verify(postsLocalDataSource).savePosts(posts)
        verify(view).onPostsReloaded()
    }

    @Test
    fun reloadPostsErrorTest() {
        val throwable = mock(Throwable::class.java)
        `when`(postsRemoteDataSource.getPosts()).thenReturn(Observable.error(throwable))
        presenter.reloadPosts()
        verify(view).onPostsReloaded()
    }

}
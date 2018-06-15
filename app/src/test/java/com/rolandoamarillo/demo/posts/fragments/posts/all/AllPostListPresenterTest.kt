package com.rolandoamarillo.demo.posts.fragments.posts.all

import com.rolandoamarillo.demo.posts.activities.main.MainContract
import com.rolandoamarillo.demo.posts.model.Post
import com.rolandoamarillo.demo.posts.repository.PostsLocalDataSource
import com.rolandoamarillo.demo.posts.repository.PostsRemoteDataSource
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.util.*

class AllPostListPresenterTest {

    lateinit var presenter: AllPostsListPresenter

    lateinit var postRemoteDataSource: PostsRemoteDataSource

    lateinit var postsLocalDataSource: PostsLocalDataSource

    lateinit var mainPresenter: MainContract.MainPresenter

    lateinit var view: AllPostListContract.AllPostListView

    val random = Random()

    @Before
    fun setup() {
        postRemoteDataSource = Mockito.mock(PostsRemoteDataSource::class.java)
        postsLocalDataSource = Mockito.mock(PostsLocalDataSource::class.java)
        mainPresenter = Mockito.mock(MainContract.MainPresenter::class.java)
        view = Mockito.mock(AllPostListContract.AllPostListView::class.java)

        presenter = AllPostsListPresenter(postRemoteDataSource, postsLocalDataSource, mainPresenter)


        val list = emptyList<Post>()
        `when`(postsLocalDataSource.needsRefresh()).thenReturn(false)
        `when`(postsLocalDataSource.getPosts()).thenReturn(Observable.just(list.toMutableList()))
        `when`(postRemoteDataSource.getPosts()).thenReturn(Observable.just(list))

        presenter.subscribe(view)
    }

    @After
    fun turndown() {
        presenter.unsubscribe()
    }

    @Test
    fun removePostTest() {
        val id = random.nextInt()
        val list = emptyList<Post>()
        `when`(postsLocalDataSource.needsRefresh()).thenReturn(false)
        `when`(postRemoteDataSource.getPosts()).thenReturn(Observable.just(list))
        presenter.removePost(id)
        verify(postsLocalDataSource).deletePost(id)
        verify(mainPresenter).resetView()
    }

    @Test
    fun removePostErrorTest() {
        val id = random.nextInt()
        val list = emptyList<Post>()
        `when`(postsLocalDataSource.needsRefresh()).thenReturn(true)
        `when`(postRemoteDataSource.getPosts()).thenReturn(Observable.just(list))
        presenter.removePost(id)
        verify(postsLocalDataSource).deletePost(id)
        verify(mainPresenter).resetView()
    }

}
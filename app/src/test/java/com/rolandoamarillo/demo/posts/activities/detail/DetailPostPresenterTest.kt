package com.rolandoamarillo.demo.posts.activities.detail

import com.rolandoamarillo.demo.posts.model.Comment
import com.rolandoamarillo.demo.posts.model.Post
import com.rolandoamarillo.demo.posts.model.User
import com.rolandoamarillo.demo.posts.repository.CommentsRemoteDataSource
import com.rolandoamarillo.demo.posts.repository.PostsLocalDataSource
import com.rolandoamarillo.demo.posts.repository.UserRemoteDataSource
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import java.util.*

class DetailPostPresenterTest {

    lateinit var presenter: DetailPostPresenter

    lateinit var userRemoteDataSource: UserRemoteDataSource

    lateinit var postsLocalDataSource: PostsLocalDataSource

    lateinit var commentsRemoteDataSource: CommentsRemoteDataSource

    lateinit var view: DetailPostContract.DetailPostView

    var random: Random = Random()

    @Before
    fun setup() {
        userRemoteDataSource = mock(UserRemoteDataSource::class.java)
        postsLocalDataSource = mock(PostsLocalDataSource::class.java)
        commentsRemoteDataSource = mock(CommentsRemoteDataSource::class.java)
        view = mock(DetailPostContract.DetailPostView::class.java)

        presenter = DetailPostPresenter(userRemoteDataSource, postsLocalDataSource, commentsRemoteDataSource)
        presenter.subscribe(view)
    }

    @After
    fun turndown() {
        presenter.unsubscribe()
    }

    @Test
    fun readPostTest() {
        val post = mock(Post::class.java)
        presenter.readPost(post)
        verify(postsLocalDataSource).savePost(post)
    }

    @Test
    fun toggleFavoriteTest() {
        val post = mock(Post::class.java)
        presenter.toggleFavorite(post)
        verify(postsLocalDataSource).savePost(post)
    }

    @Test
    fun getUserTest() {
        val postId = random.nextInt()
        val user = mock(User::class.java)
        `when`(userRemoteDataSource.getUser(eq(postId))).thenReturn(Observable.just(user))
        presenter.getUser(postId)
        verify(view).onUserRetrieved(user)
    }

    @Test
    fun getUserErrorTest() {
        val userId = random.nextInt()
        val error = mock(Throwable::class.java)
        `when`(userRemoteDataSource.getUser(eq(userId))).thenReturn(Observable.error(error))
        presenter.getUser(userId)
        verify(view).onUserError(error)
    }

    @Test
    fun getCommentsTest() {
        val postId = random.nextInt()
        val comments = emptyList<Comment>()
        `when`(commentsRemoteDataSource.getComments(eq(postId))).thenReturn(Observable.just(comments))
        presenter.getComments(postId)
        verify(view).onCommentsRetrieved(comments)
    }

    @Test
    fun getCommentsErrorTest() {
        val postId = random.nextInt()
        val error = mock(Throwable::class.java)
        `when`(commentsRemoteDataSource.getComments(eq(postId))).thenReturn(Observable.error(error))
        presenter.getComments(postId)
        verify(view).onCommentsError(error)
    }
}
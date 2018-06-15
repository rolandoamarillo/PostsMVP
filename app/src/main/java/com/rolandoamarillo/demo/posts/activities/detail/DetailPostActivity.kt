package com.rolandoamarillo.demo.posts.activities.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.rolandoamarillo.demo.posts.R
import com.rolandoamarillo.demo.posts.model.Comment
import com.rolandoamarillo.demo.posts.model.Post
import com.rolandoamarillo.demo.posts.model.User
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_post_detail.*
import javax.inject.Inject

class DetailPostActivity : AppCompatActivity(), DetailPostContract.DetailPostView {

    companion object {

        private const val POST_PARAM: String = "postParam"

        fun createIntent(context: Context, post: Post): Intent {
            val intent = Intent(context, DetailPostActivity::class.java)
            intent.putExtra(POST_PARAM, post)
            return intent
        }

    }

    @Inject
    lateinit var presenter: DetailPostContract.DetailPostPresenter

    lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        post = intent.getParcelableExtra(POST_PARAM)

        titleTextView.text = post.title
        bodyTextView.text = post.body

        userProgress.visibility = View.VISIBLE
        userLayout.visibility = View.INVISIBLE

        presenter.readPost(post)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (post.favorite) {
            menuInflater.inflate(R.menu.menu_detail_post_unfavorite, menu)
        } else {
            menuInflater.inflate(R.menu.menu_detail_post_favorite, menu)
        }
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.clear()
        if (post.favorite) {
            menuInflater.inflate(R.menu.menu_detail_post_unfavorite, menu)
        } else {
            menuInflater.inflate(R.menu.menu_detail_post_favorite, menu)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_favorite) {
            presenter.toggleFavorite(post)
            invalidateOptionsMenu()
            return true
        } else if (id == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        presenter.subscribe(this)
        post.userId?.let { presenter.getUser(it) }
        post.id?.let { presenter.getComments(it) }
    }

    override fun onDestroy() {
        presenter.unsubscribe()
        super.onDestroy()
    }

    override fun onUserRetrieved(user: User) {
        userNameTextView.text = getString(R.string.user_name, user.name)
        userEmailTextView.text = getString(R.string.user_email, user.email)
        userPhoneTextView.text = getString(R.string.user_phone, user.phone)
        userWebsiteTextView.text = getString(R.string.user_website, user.website)
        userProgress.visibility = View.GONE
        userLayout.visibility = View.VISIBLE
    }

    override fun onUserError(throwable: Throwable) {
        Toast.makeText(this, R.string.generic_error, Toast.LENGTH_SHORT).show()
    }

    override fun onCommentsRetrieved(comments: List<Comment>) {
        commentsLayout.removeAllViews()
        val inflater = LayoutInflater.from(this)
        for (comment in comments) {
            val view = inflater.inflate(R.layout.comment_row, commentsLayout, false)
            val nameTextView = view.findViewById<TextView>(R.id.nameTextView)
            nameTextView.text = comment.name
            val bodyTextView = view.findViewById<TextView>(R.id.bodyTextView)
            bodyTextView.text = comment.body
            commentsLayout.addView(view)
        }
    }

    override fun onCommentsError(throwable: Throwable) {
        Toast.makeText(this, R.string.generic_error, Toast.LENGTH_SHORT).show()
    }
}

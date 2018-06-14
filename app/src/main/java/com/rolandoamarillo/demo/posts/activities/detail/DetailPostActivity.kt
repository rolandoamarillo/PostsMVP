package com.rolandoamarillo.demo.posts.activities.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.rolandoamarillo.demo.posts.R
import com.rolandoamarillo.demo.posts.model.Post
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
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_refresh) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        presenter.subscribe(this)
    }

    override fun onDestroy() {
        presenter.unsubscribe()
        super.onDestroy()
    }

    override fun onUserRetrieved() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCommentsRetrieved() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCommentsError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

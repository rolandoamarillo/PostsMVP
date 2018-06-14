package com.rolandoamarillo.demo.posts.fragments.posts.favorites

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rolandoamarillo.demo.posts.R
import com.rolandoamarillo.demo.posts.activities.detail.DetailPostActivity
import com.rolandoamarillo.demo.posts.fragments.posts.adapter.PostsAdapter
import com.rolandoamarillo.demo.posts.model.Post
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_favorites_posts.*
import javax.inject.Inject

class FavoritesPostListFragment : Fragment(), FavoritesPostsListContract.FavoritesPostListView, PostsAdapter.PostClickListener {

    companion object {

        /**
         * Returns a new instance of this fragment to show all the posts
         * number.
         */
        fun newInstance(): FavoritesPostListFragment {
            val fragment = FavoritesPostListFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var presenter: FavoritesPostsListContract.FavoritesListPresenter

    lateinit var adapter: PostsAdapter

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        adapter = PostsAdapter(this)
        recyclerView.adapter = adapter

        progressView.visibility = View.VISIBLE
        errorMessageTextView.visibility = View.GONE
        recyclerView.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()
        presenter.subscribe(this)
    }

    override fun onDestroyView() {
        presenter.unsubscribe()
        super.onDestroyView()
    }

    override fun onPostsLoaded(posts: List<Post>) {
        recyclerView.visibility = View.VISIBLE
        errorMessageTextView.visibility = View.GONE
        progressView.visibility = View.GONE
        adapter.setPosts(posts)
    }

    override fun onPostsError(throwable: Throwable) {
        recyclerView.visibility = View.GONE
        errorMessageTextView.visibility = View.VISIBLE
        progressView.visibility = View.GONE
    }

    override fun showProgress() {
        recyclerView.visibility = View.GONE
        errorMessageTextView.visibility = View.GONE
        progressView.visibility = View.VISIBLE
    }

    override fun onPostClicked(post: Post) {
        context?.let {
            startActivity(DetailPostActivity.createIntent(it, post))
        }
    }
}
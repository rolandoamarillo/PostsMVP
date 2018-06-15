package com.rolandoamarillo.demo.posts.fragments.posts.all

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rolandoamarillo.demo.posts.R
import com.rolandoamarillo.demo.posts.activities.detail.DetailPostActivity
import com.rolandoamarillo.demo.posts.fragments.posts.adapter.PostsAdapter
import com.rolandoamarillo.demo.posts.model.Post
import com.rolandoamarillo.demo.posts.util.SwipeToDeleteCallback
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_all_posts.*
import javax.inject.Inject

class AllPostListFragment : Fragment(), AllPostListContract.AllPostListView, PostsAdapter.PostClickListener {

    companion object {

        /**
         * Returns a new instance of this fragment to show all the posts
         */
        fun newInstance(): AllPostListFragment {
            val fragment = AllPostListFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var presenter: AllPostListContract.AllPostListPresenter

    lateinit var adapter: PostsAdapter

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_all_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        adapter = PostsAdapter(this)
        recyclerView.adapter = adapter

        val swipeHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as PostsAdapter
                val position = viewHolder.adapterPosition
                val post = adapter.getPost(position)
                adapter.removeAt(position)
                post.id?.let { presenter.removePost(it) }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)

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
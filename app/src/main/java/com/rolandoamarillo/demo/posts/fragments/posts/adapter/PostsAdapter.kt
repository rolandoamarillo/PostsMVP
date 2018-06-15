package com.rolandoamarillo.demo.posts.fragments.posts.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rolandoamarillo.demo.posts.R
import com.rolandoamarillo.demo.posts.model.Post
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_row_post.*

class PostsAdapter(private val postClickListener: PostClickListener) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    private val items = ArrayList<Post>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_row_post, parent, false)
        return PostViewHolder(view, postClickListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setPosts(posts: List<Post>) {
        items.clear()
        items.addAll(posts)
        notifyDataSetChanged()
    }

    fun getPost(position: Int): Post {
        return items[position]
    }

    class PostViewHolder(override val containerView: View, private val postClickListener: PostClickListener) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(post: Post) {
            containerView.tag = post
            containerView.setOnClickListener({ v -> postClickListener.onPostClicked(v.tag as Post) })
            with(post)
            {
                titleTextView.text = title
                bodyTextView.text = body
                if (notRead) {
                    dotImageView.visibility = View.VISIBLE
                } else {
                    dotImageView.visibility = View.INVISIBLE
                }

                if (favorite) {
                    favoriteImageView.visibility = View.VISIBLE
                } else {
                    favoriteImageView.visibility = View.INVISIBLE
                }
            }
        }
    }

    fun removeAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    interface PostClickListener {
        fun onPostClicked(post: Post)
    }
}
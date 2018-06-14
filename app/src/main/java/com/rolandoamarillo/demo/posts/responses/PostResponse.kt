package com.rolandoamarillo.demo.posts.responses

import com.google.gson.annotations.SerializedName

class PostResponse(@SerializedName("userId") val userId: Int,
                   @SerializedName("id") val id: Int,
                   @SerializedName("title") val title: String,
                   @SerializedName("body") val body: String,
                   var notRead: Boolean,
                   var favorite: Boolean)
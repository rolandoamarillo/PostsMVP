package com.rolandoamarillo.demo.posts.model

import com.google.gson.annotations.SerializedName

class User(@SerializedName("name") val name: String,
           @SerializedName("email") val email: String,
           @SerializedName("phone") val phone: String,
           @SerializedName("website") val website: String)
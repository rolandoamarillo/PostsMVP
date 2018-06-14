package com.rolandoamarillo.demo.posts.model

import android.os.Parcel
import android.os.Parcelable
import com.rolandoamarillo.demo.posts.responses.PostResponse
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Post() : RealmObject(), Parcelable {

    var userId: Int? = null
    @PrimaryKey
    var id: Int? = null
    var title: String? = null
    var body: String? = null
    var notRead: Boolean = false
    var favorite: Boolean = false

    constructor(parcel: Parcel) : this() {
        userId = parcel.readValue(Int::class.java.classLoader) as? Int
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        title = parcel.readString()
        body = parcel.readString()
        notRead = parcel.readByte() != 0.toByte()
        favorite = parcel.readByte() != 0.toByte()
    }

    constructor(postResponse: PostResponse) : this() {
        userId = postResponse.userId
        id = postResponse.id
        title = postResponse.title
        body = postResponse.body
        notRead = postResponse.notRead
        favorite = postResponse.favorite
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(userId)
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeString(body)
        parcel.writeByte(if (notRead) 1 else 0)
        parcel.writeByte(if (favorite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }

        fun parseResponse(postResponse: PostResponse): Post {
            return Post(postResponse)
        }
    }
}
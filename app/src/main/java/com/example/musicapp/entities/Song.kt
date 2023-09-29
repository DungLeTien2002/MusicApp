package com.example.musicapp.entities

import com.google.gson.annotations.SerializedName

data class Song(
    @SerializedName("singer") var singer: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("audio") var audio: String? = null,
    @SerializedName("year") var year: String? = null,
    @SerializedName("length") var length: String? = null,
    @SerializedName("playCount") var playCount: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("id") var id: String? = null,

)

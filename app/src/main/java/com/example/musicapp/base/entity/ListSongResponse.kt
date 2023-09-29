package com.example.musicapp.base.entity

data class ListSongResponse(
    val status:Int,
    val message:String,
    val data:ListSongResult
)

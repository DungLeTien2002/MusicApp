package com.example.musicapp.base.entity

data class RegisterResponse(
    val status:Int,
    val message:String,
    val data: RegisterResponseData
)

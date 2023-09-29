package com.example.musicapp.base.entity

data class LoginResponse(
    val status: Int,
    val message: String,
    val accessToken: String
)

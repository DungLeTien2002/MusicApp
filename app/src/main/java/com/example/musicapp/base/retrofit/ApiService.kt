package com.example.musicapp.base.retrofit

import com.example.musicapp.base.entity.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("/api/auth/login")
    fun login(@Body loginRequest: LoginRequest):Call<LoginResponse>

    @POST("/api/auth/register")
    fun register(@Body registerRequest: RegisterRequest):Call<RegisterResponse>

    @GET("/api/musics?limit=200")
    fun getSong(@Header("Authorization") accessToken:String):Call<ListSongResponse>
}
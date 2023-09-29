package com.example.musicapp.base.base_view

sealed class DataResult<out T> {
    data class Success<T>(val data:T):DataResult<T>()
    data class Error(val exception: Exception):DataResult<Nothing>()
}
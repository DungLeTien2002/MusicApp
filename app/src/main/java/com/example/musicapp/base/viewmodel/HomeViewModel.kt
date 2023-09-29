package com.example.musicapp.base.viewmodel


import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.lifecycle.MutableLiveData
import com.example.musicapp.base.base_view.BaseViewModel
import com.example.musicapp.base.base_view.DataResult
import com.example.musicapp.base.retrofit.ApiService
import com.example.musicapp.base.retrofit.RetrofitClient
import com.example.musicapp.entities.Song
import kotlin.coroutines.suspendCoroutine
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.musicapp.base.entity.ListSongResponse
import retrofit2.Call
import retrofit2.Response
import kotlin.coroutines.resume


open class HomeViewModel : BaseViewModel() {
    private val _listSong = MutableLiveData<MutableList<Song>>()
    val listSong: LiveData<MutableList<Song>>
        get() = _listSong
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor

    fun getSongAtHome(context: Context) {
        sharedPreferences = context?.getSharedPreferences("my_sharedPreferences", Context.MODE_PRIVATE)
            ?: sharedPreferences
        val accessToken = sharedPreferences.getString("accessToken", "")

        executeTask(
            request = { accessToken?.let { getListSong(it) }!! },
            onSuccess = {
                _listSong.value = it
            },
            onError = {

            },
            showLoading = true
        )
    }

    suspend fun getListSong(accessToken: String): DataResult<MutableList<Song>> {
        var list = mutableListOf<Song>()
        return suspendCoroutine {
            val apiService = RetrofitClient.getInstance().create(ApiService::class.java)
            val call = apiService.getSong("Bearer $accessToken")
            call.enqueue(object : retrofit2.Callback<ListSongResponse> {
                override fun onResponse(
                    call: Call<ListSongResponse>,
                    response: Response<ListSongResponse>
                ) {
                    if (response.isSuccessful && response.body()?.data != null)
                        list = response.body()?.data!!.results.toMutableList()
                    it.resume(DataResult.Success(list))
                }

                override fun onFailure(call: Call<ListSongResponse>, t: Throwable) {
                    it.resume(DataResult.Success(list))
                }
            })
        }
    }
}
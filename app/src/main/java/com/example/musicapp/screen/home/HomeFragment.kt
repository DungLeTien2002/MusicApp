package com.example.musicapp.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.R
import com.example.musicapp.adapter.SongAdapter
import com.example.musicapp.base.base_view.BaseFragment
import com.example.musicapp.base.viewmodel.HomeViewModel
import com.example.musicapp.databinding.FragmentHomeBinding
import com.example.musicapp.entities.Song


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    val viewModel by lazy {
        activity?.let { ViewModelProvider(it) }?.get(HomeViewModel::class.java)
    }
    private val songAdapter by lazy {
        SongAdapter()
    }

    override fun initListener() {

    }

    override fun initData() {
        viewModel?.getSongAtHome(requireContext())
        viewModel?.listSong?.observe(this) {
            viewModel!!.listSong.value?.let { it1 ->
                songAdapter.setDataList(it1)
            }
        }
    }

    override fun initView() {
        val adapter = songAdapter
        binding.listSong.adapter = adapter
        binding.listSong.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }
}
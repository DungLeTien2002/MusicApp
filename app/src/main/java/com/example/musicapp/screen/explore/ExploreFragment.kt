package com.example.musicapp.screen.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicapp.R
import com.example.musicapp.base.base_view.BaseFragment
import com.example.musicapp.databinding.FragmentExploreBinding


class ExploreFragment : BaseFragment<FragmentExploreBinding>() {
    override fun initListener() {

    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExploreBinding {
        return FragmentExploreBinding.inflate(inflater,container,false)
    }
}
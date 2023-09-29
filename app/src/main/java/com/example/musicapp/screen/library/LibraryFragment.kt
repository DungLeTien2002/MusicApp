package com.example.musicapp.screen.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicapp.R
import com.example.musicapp.base.base_view.BaseFragment
import com.example.musicapp.databinding.FragmentLibraryBinding


class LibraryFragment : BaseFragment<FragmentLibraryBinding>() {
    override fun initListener() {

    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLibraryBinding {
        return FragmentLibraryBinding.inflate(inflater,container,false)
    }
}
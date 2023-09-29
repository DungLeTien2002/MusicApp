package com.example.musicapp.screen.forgot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicapp.R
import com.example.musicapp.base.base_view.BaseFragment
import com.example.musicapp.databinding.FragmentConfirmCodeBinding


class ConfirmCodeFragment : BaseFragment<FragmentConfirmCodeBinding>() {
    override fun initListener() {

    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentConfirmCodeBinding {
        return FragmentConfirmCodeBinding.inflate(inflater,container,false)
    }
}
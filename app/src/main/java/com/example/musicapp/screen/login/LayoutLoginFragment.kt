package com.example.musicapp.screen.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.musicapp.R
import com.example.musicapp.base.base_view.BaseFragment
import com.example.musicapp.databinding.FragmentLayoutLoginBinding
import com.example.musicapp.screen.sign_up.SignUpFragment


class LayoutLoginFragment : BaseFragment<FragmentLayoutLoginBinding>() {
    override fun initListener() {
        binding.goLayoutSignIn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.login,SignInFragment()).addToBackStack(null).commit()
        }
        binding.tvSignUp.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.login,SignUpFragment()).addToBackStack(null).commit()
        }
    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLayoutLoginBinding {
        return FragmentLayoutLoginBinding.inflate(inflater,container,false)
    }
}
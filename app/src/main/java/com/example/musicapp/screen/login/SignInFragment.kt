package com.example.musicapp.screen.login

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.graphics.Color
import android.graphics.ColorFilter
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicapp.MainActivity
import com.example.musicapp.R
import com.example.musicapp.base.base_view.BaseFragment
import com.example.musicapp.base.entity.LoginRequest
import com.example.musicapp.base.entity.LoginResponse
import com.example.musicapp.base.retrofit.ApiService
import com.example.musicapp.base.retrofit.RetrofitClient
import com.example.musicapp.base.utils.Constant
import com.example.musicapp.base.utils.extension.ExtensionFunction.toast
import com.example.musicapp.base.utils.extension.ExtensionFunction.toastLong
import com.example.musicapp.databinding.FragmentSignInBinding
import com.example.musicapp.screen.forgot.ForgotPasswordFragment
import com.example.musicapp.screen.sign_up.SignUpFragment
import retrofit2.Call
import retrofit2.Response


class SignInFragment : BaseFragment<FragmentSignInBinding>() {
    private var progressDialog:ProgressDialog?=null
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor
    private var rememberMe = false
    override fun initListener() {
        binding.backSignIn.setOnClickListener {
            (activity as LoginActivity)?.onBackPressed()
        }
        binding.tvSignUp.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.login, SignUpFragment()).addToBackStack(null).commit()
        }
        binding.goForgotPassword.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.login, ForgotPasswordFragment()).addToBackStack(null).commit()
        }
        var hidePassword = true
        binding.hidePassword.setOnClickListener {
            //ko hien password
            if (hidePassword) {
                //show
                binding.hidePassword.setImageResource(R.drawable.show_password)
                binding.password.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                hidePassword = false
            } else {
                hidePassword = true
                binding.hidePassword.setImageResource(R.drawable.hide_pass)
                binding.password.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        binding.rememberMe.setOnClickListener {
            if (rememberMe) {
                binding.rememberMe.setColorFilter(Color.parseColor("#FFFFFFFF"))
                rememberMe = false

            } else {
                binding.rememberMe.setColorFilter(Color.parseColor("#06C149"))
                rememberMe = true
            }
        }
        binding.LoginAccount.setOnClickListener {
            var user = binding.userName.text.toString()
            var password = binding.password.text.toString()
            login(user, password)

        }
    }

    private fun login(user: String, password: String) {
        if (user == "") {
            requireContext().toast(R.string.username_cannot_be_left_blank.toString())
        }
        if (password == "") {
            requireContext().toast(R.string.password_cannot_be_left_blank.toString())
        }
        if (user != "" && password != "") {
            progressDialog = ProgressDialog.show(context, null, getString(R.string.logging), true)

            val apiService = RetrofitClient.getInstance().create(ApiService::class.java)
            val loginRequest = LoginRequest(user, password)
            val call = apiService.login(loginRequest)
            call.enqueue(object : retrofit2.Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    progressDialog?.dismiss()
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        if (loginResponse != null) {

                            val accessToken = loginResponse.accessToken

                            editor.putString("accessToken","$accessToken")
                            editor.putBoolean("isRemember",rememberMe)
                            editor.putString("userName","$user")
                            editor.putString("password","$password")

                        }
                        editor.apply()
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        requireContext().toast(getString(R.string.username_or_password_is_incorrect))

                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    progressDialog?.dismiss()
                    requireContext().toastLong("${t.message}")
                }
            })
        }
    }

    override fun initData() {

    }

    override fun initView() {
        sharedPreferences = context?.getSharedPreferences("my_sharedPreferences",Context.MODE_PRIVATE)?:sharedPreferences
        editor=sharedPreferences.edit()
    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignInBinding {
        return FragmentSignInBinding.inflate(inflater, container, false)
    }
}
package com.example.musicapp.screen.sign_up

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.musicapp.MainActivity
import com.example.musicapp.R
import com.example.musicapp.base.base_view.BaseFragment
import com.example.musicapp.base.entity.*
import com.example.musicapp.base.retrofit.ApiService
import com.example.musicapp.base.retrofit.RetrofitClient
import com.example.musicapp.base.utils.extension.ExtensionFunction.toast
import com.example.musicapp.databinding.FragmentSignUpBinding
import com.example.musicapp.screen.login.LoginActivity
import com.example.musicapp.screen.login.SignInFragment
import retrofit2.Call
import retrofit2.Response


class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {
    private var progressDialog: ProgressDialog? = null
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor:SharedPreferences.Editor
    override fun initListener() {
        binding.backSignUp.setOnClickListener {
            (activity as LoginActivity)?.onBackPressed()
        }
        var rememberMe = false
        binding.rememberMe.setOnClickListener {
            if (rememberMe) {
                binding.rememberMe.setColorFilter(Color.parseColor("#FFFFFFFF"))
                rememberMe = false

            } else {
                binding.rememberMe.setColorFilter(Color.parseColor("#06C149"))
                rememberMe = true
            }
        }
        binding.tvSignIn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.login, SignInFragment()).addToBackStack(null).commit()
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
        var hideConfirmPassword = true
        binding.hideConfirmPassword.setOnClickListener {
            if (hideConfirmPassword) {
                hideConfirmPassword = false
                binding.hideConfirmPassword.setImageResource(R.drawable.show_password)
                binding.confirmPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()

            } else {
                hideConfirmPassword = true
                binding.hideConfirmPassword.setImageResource(R.drawable.hide_pass)
                binding.confirmPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }

        }
        binding.signUp.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val username: String = binding.userName.text.toString()
        val fullName: String = binding.fullName.text.toString()
        val email: String = binding.email.text.toString()
        val password: String = binding.password.text.toString()
        val passwordAgain: String = binding.confirmPassword.text.toString()
        if (username == "") {
            requireContext().toast(R.string.username_cannot_be_left_blank.toString())
        }
        if (fullName == "") {
            requireContext().toast(R.string.fullname_cannot_be_left_blank.toString())
        }
        if (email == "") {
            requireContext().toast(R.string.email_cannot_be_left_blank.toString())
        }
        if (password == "") {
            requireContext().toast(R.string.password_cannot_be_left_blank.toString())
        }
        if (passwordAgain == "") {
            requireContext().toast(R.string.password_again_cannot_be_left_blank.toString())
        }
        if (password != passwordAgain) {
            requireContext().toast(R.string.password_and_password_again_must_be_same.toString())
        }
        if (username != "" && fullName != "" && email != "" && password != "" && passwordAgain != "" && password == passwordAgain) {
            progressDialog = ProgressDialog.show(
                context,
                null,
                getString(R.string.registering_please_wait),
                true
            )
            val apiService = RetrofitClient.getInstance().create(ApiService::class.java)
            val registerRequest =
                RegisterRequest(fullName, username, email, password, passwordAgain)
            val call = apiService.register(registerRequest)
            call.enqueue(object : retrofit2.Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    progressDialog?.dismiss()
                    if (response.isSuccessful) {
                        val data: RegisterResponseData? =response?.body()?.data
                        val loginRequest = LoginRequest(username, password)
                        val call2 = apiService.login(loginRequest)
                        call2.enqueue(object : retrofit2.Callback<LoginResponse> {
                            override fun onResponse(
                                call: Call<LoginResponse>,
                                response: Response<LoginResponse>
                            ) {

                                if (response.isSuccessful) {
                                    val loginResponse = response.body()
                                    if (loginResponse != null) {
                                        val accessToken = loginResponse.accessToken
                                        editor.putString("accessToken","$accessToken")
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
                                requireContext().toast("${t.message}")
                            }
                        })
                    }

                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    progressDialog?.dismiss()
                    requireContext().toast("${t.message}")
                }
            })
        }
    }

    override fun initData() {

    }

    override fun initView() {
        sharedPreferences = context?.getSharedPreferences("my_sharedPreferences", Context.MODE_PRIVATE)?:sharedPreferences
        editor=sharedPreferences.edit()
    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignUpBinding {
        return FragmentSignUpBinding.inflate(inflater, container, false)
    }
}
package com.example.musicapp.screen.login

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.musicapp.MainActivity
import com.example.musicapp.R
import com.example.musicapp.base.base_view.BaseActivity
import com.example.musicapp.base.entity.LoginRequest
import com.example.musicapp.base.entity.LoginResponse
import com.example.musicapp.base.retrofit.ApiService
import com.example.musicapp.base.retrofit.RetrofitClient
import com.example.musicapp.base.utils.extension.ExtensionFunction.toast
import com.example.musicapp.base.utils.extension.ExtensionFunction.toastLong
import com.example.musicapp.databinding.ActivityLoginBinding
import com.example.musicapp.screen.ExitAppBottomSheetDialog
import com.example.musicapp.screen.sign_up.SignUpFragment
import retrofit2.Call
import retrofit2.Response

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor
    private var progressDialog: ProgressDialog? = null

    override fun initListener() {

    }

    override fun initData() {

    }

    override fun initView() {
        sharedPreferences = this?.getSharedPreferences("my_sharedPreferences", Context.MODE_PRIVATE)
            ?: sharedPreferences
        editor = sharedPreferences.edit()
        val isRemember: Boolean = sharedPreferences.getBoolean("isRemember", false)
//        Log.e("jsnd","$isRemember")
        if (isRemember) {
            //auto login
            progressDialog =
                ProgressDialog.show(this, null, getString(R.string.logging_in_please_wait), true)
            val userName = sharedPreferences.getString("userName", null)
            val password = sharedPreferences.getString("password", null)
            if (userName == null || password == null) {
                progressDialog?.dismiss()
                this.toast(getString(R.string.login_failed))
                val fragmentManager: FragmentManager = supportFragmentManager
                fragmentManager.beginTransaction().replace(R.id.login, LayoutLoginFragment())
                    .addToBackStack(null).commit()
            } else {

                val apiService = RetrofitClient.getInstance().create(ApiService::class.java)
                val loginRequest = userName?.let { password?.let { it1 -> LoginRequest(it, it1) } }
                val call = loginRequest?.let { apiService.login(it) }
                call?.enqueue(object : retrofit2.Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            progressDialog?.dismiss()
                            val loginResponse = response.body()
                            if (loginResponse != null) {
                                val accessToken = loginResponse.accessToken
                                editor.putString("accessToken", "$accessToken")

                            }
                            editor.apply()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            //requireContext().toast(getString(R.string.username_or_password_is_incorrect))
                            this@LoginActivity.toast(getString(R.string.username_or_password_is_incorrect))

                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        progressDialog?.dismiss()
                        //.toastLong("${t.message}")
                        this@LoginActivity.toastLong("${t.message}")
                    }
                })
            }


        } else {
            val fragmentManager: FragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.login, LayoutLoginFragment())
                .addToBackStack(null).commit()
        }


    }

    override fun onBackPressed() {


        if (supportFragmentManager.backStackEntryCount == 1) {
            //bottom logout
            val exitAppBottomSheetDialog = ExitAppBottomSheetDialog()
            exitAppBottomSheetDialog.show(supportFragmentManager, "")
            exitAppBottomSheetDialog.setOnClickListener(object :
                ExitAppBottomSheetDialog.OnClickListener {
                override fun onYesClick() {
                    finishAffinity()
                }

                override fun onCancelClick() {
                    exitAppBottomSheetDialog.dismiss()
                }
            })
            Log.e("fragmentReplace", "${supportFragmentManager.backStackEntryCount}")
        } else {
            supportFragmentManager.popBackStack();
        }
    }

    override fun inflateViewBinding(layoutInflater: LayoutInflater): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }
}
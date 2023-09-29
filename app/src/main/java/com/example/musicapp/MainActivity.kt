package com.example.musicapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.musicapp.adapter.ViewPagerAdapter
import com.example.musicapp.base.base_view.BaseActivity
import com.example.musicapp.databinding.ActivityMainBinding
import com.example.musicapp.screen.LogoutBottomSheetDialog
import com.example.musicapp.screen.login.LayoutLoginFragment
import com.example.musicapp.screen.login.LoginActivity
import com.example.musicapp.screen.login.SignInFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor
    override fun initListener() {
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (binding.viewPager.currentItem) {
                    0 -> {
                        binding.btnNav.menu.findItem(R.id.home).isChecked = true
                    }1 -> {
                        binding.btnNav.menu.findItem(R.id.explore).isChecked = true
                    }2 -> {
                        binding.btnNav.menu.findItem(R.id.library).isChecked = true
                    }3 -> {
                        binding.btnNav.menu.findItem(R.id.profile).isChecked = true
                    }
                }
            }
        })
        binding.btnNav.setOnItemSelectedListener {item ->
            when(item.itemId){
                R.id.home->{
                    binding.viewPager.currentItem=0
                    true
                } R.id.explore->{
                    binding.viewPager.currentItem=1
                    true
                } R.id.library->{
                    binding.viewPager.currentItem=2
                    true
                }
                R.id.profile -> {
                    binding.viewPager.currentItem = 3
                    true
                }
                else->false

            }

        }



    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount==0){
            //hien log out account va back signFragment
            val logoutBottomSheetDialog=LogoutBottomSheetDialog()
            logoutBottomSheetDialog.show(supportFragmentManager,"")
            logoutBottomSheetDialog.setOnClickListener(object :LogoutBottomSheetDialog.OnClickListener{
                override fun onYesClick() {
                    editor.putBoolean("isRemember",false)
                    editor.putString("userName",null)
                    editor.putString("password",null)
                    editor.apply()
                    val intent= Intent(this@MainActivity,LoginActivity::class.java)
                    startActivity(intent)
                }

                override fun onNoClick() {
                    logoutBottomSheetDialog.dismiss()
                }
            })

        }else{
            if (supportFragmentManager.backStackEntryCount == 1) {
                binding.viewPager.visibility= View.VISIBLE
                binding.btnNav.visibility= View.VISIBLE
                supportFragmentManager.popBackStack();

            } else {


                binding.viewPager.visibility= View.GONE
                binding.btnNav.visibility= View.GONE
                supportFragmentManager.popBackStack();
            }
        }

    }

    override fun initData() {

    }

    override fun initView() {
        sharedPreferences=this?.getSharedPreferences("my_sharedPreferences",Context.MODE_PRIVATE)?:sharedPreferences
        editor=sharedPreferences.edit()
    }

    override fun inflateViewBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}
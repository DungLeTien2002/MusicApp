package com.example.musicapp.base.base_view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding> :AppCompatActivity() {

    lateinit var binding:VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=inflateViewBinding(layoutInflater)
        setContentView(binding.root)
        initView()
        initData()
        initListener()
    }

    abstract fun initListener()

    abstract fun initData()

    abstract fun initView()

    abstract fun inflateViewBinding(layoutInflater: LayoutInflater): VB
}
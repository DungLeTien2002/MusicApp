package com.example.musicapp.screen

import android.view.LayoutInflater

import com.example.musicapp.base.base_view.BaseBottomSheetDialog
import com.example.musicapp.databinding.BottomLogoutBinding

class LogoutBottomSheetDialog:BaseBottomSheetDialog<BottomLogoutBinding>() {
    private var logoutClickListener:OnClickListener?=null
    interface OnClickListener{
        fun onYesClick()
        fun onNoClick()
    }
    fun setOnClickListener(listener:OnClickListener){
        logoutClickListener=listener
    }

    override fun initListener() {
        binding.cancelLogout.setOnClickListener{
            logoutClickListener?.onNoClick()
        }
        binding.yesLogout.setOnClickListener{
            logoutClickListener?.onYesClick()
        }
    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun inflateBinding(layoutInflater: LayoutInflater): BottomLogoutBinding {
       return BottomLogoutBinding.inflate(layoutInflater)
    }
}
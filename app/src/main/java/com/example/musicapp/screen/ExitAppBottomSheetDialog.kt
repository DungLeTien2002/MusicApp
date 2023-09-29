package com.example.musicapp.screen

import android.view.LayoutInflater
import com.example.musicapp.base.base_view.BaseBottomSheetDialog
import com.example.musicapp.databinding.BottomExitappBinding

class ExitAppBottomSheetDialog:BaseBottomSheetDialog<BottomExitappBinding>() {
    private var exitAppClickListener:OnClickListener?=null
    interface OnClickListener{
        fun onYesClick()
        fun onCancelClick()
    }
    fun setOnClickListener(listener:OnClickListener){
        exitAppClickListener=listener
    }
    override fun initListener() {
        binding.cancelLogout.setOnClickListener{
            exitAppClickListener?.onCancelClick()
        }
        binding.yesLogout.setOnClickListener{
            exitAppClickListener?.onYesClick()
        }
    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun inflateBinding(layoutInflater: LayoutInflater): BottomExitappBinding {
       return BottomExitappBinding.inflate(layoutInflater)
    }
}
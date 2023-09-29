package com.example.musicapp.base.base_view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.musicapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialog<VB: ViewBinding>:BottomSheetDialogFragment() {

    private var _binding:VB?=null
    lateinit var binding:VB
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog=super.onCreateDialog(savedInstanceState)
        return dialog
    }

    open fun expandedOffset():Float{
        return 0.85f
    }

    open fun isDraggable():Boolean{
        return true
    }

    @Suppress("UNCHECKEd_CAST")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=inflateBinding(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initListener()
    }

    abstract fun initListener()

    abstract fun initData()

    abstract fun initView()

    abstract fun inflateBinding(layoutInflater: LayoutInflater): VB

    override fun getTheme(): Int= R.style.BaseBottomSheetDialog
}
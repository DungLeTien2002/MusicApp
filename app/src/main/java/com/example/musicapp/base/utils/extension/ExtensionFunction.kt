package com.example.musicapp.base.utils.extension

import android.content.Context
import android.widget.Toast

object ExtensionFunction {
    fun Context.toast(s:String){
        Toast.makeText(this,s,Toast.LENGTH_SHORT)
    }
    fun Context.toastLong(s:String){
        Toast.makeText(this,s,Toast.LENGTH_LONG)
    }
}
package com.example.musicapp.screen.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.musicapp.MainActivity
import com.example.musicapp.R
import com.example.musicapp.screen.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(
            {
                val intent:Intent=Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            },4000
        )
    }
}
package com.getit.getit.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.getit.getit.databinding.ActivityLoginBinding
import com.getit.getit.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginSignInBtn.setOnClickListener {
            Log.d("TAG","click login btn")
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
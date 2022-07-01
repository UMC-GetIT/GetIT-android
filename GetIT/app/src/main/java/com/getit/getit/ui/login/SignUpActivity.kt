package com.getit.getit.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.getit.getit.databinding.ActivitySignupBinding
import com.getit.getit.ui.main.MainActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signUpSignUpBtn.setOnClickListener {

        }

        binding.signUpBackIv.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
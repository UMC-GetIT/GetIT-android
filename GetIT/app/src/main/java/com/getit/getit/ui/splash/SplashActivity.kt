package com.getit.getit.ui.splash

import android.content.Intent
import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.getit.getit.databinding.ActivityLoginBinding
import com.getit.getit.databinding.ActivityMainBinding
import com.getit.getit.databinding.ActivitySplashBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.ui.login.LoginActivity
import com.getit.getit.ui.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate)   {

    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
        }, 1000)

    }

    override fun initAfterBinding() {
    }
}
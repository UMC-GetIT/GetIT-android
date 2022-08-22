package com.getit.getit.ui.main.mypage.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.getit.getit.databinding.SettingDeveloperInfoBinding

class DeveloperInfoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = SettingDeveloperInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뒤로가기 버튼
        binding.backspaceBtn.setOnClickListener {
            super.onBackPressed()
        }

    }
}
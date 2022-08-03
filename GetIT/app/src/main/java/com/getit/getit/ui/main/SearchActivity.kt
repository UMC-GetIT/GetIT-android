package com.getit.getit.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.getit.getit.databinding.ActivitySearchBinding

class SearchActivity: AppCompatActivity() {
    lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backspaceBtn.setOnClickListener {
            super.onBackPressed()
        }
    }
}
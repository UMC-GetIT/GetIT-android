package com.getit.getit.ui.main.mypage.Review

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.getit.getit.databinding.FragmentSettingsBinding
import com.getit.getit.databinding.MypageReviewListBinding

class ReviewList : AppCompatActivity() {
    lateinit var binding: MypageReviewListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MypageReviewListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뒤로가기 버튼
        binding.backspaceBtn.setOnClickListener {
            super.onBackPressed()
        }
    }
}
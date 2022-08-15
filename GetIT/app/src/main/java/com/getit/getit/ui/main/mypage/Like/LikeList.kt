package com.getit.getit.ui.main.mypage.Like

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.getit.getit.databinding.FragmentSettingsBinding
import com.getit.getit.databinding.MypageLikeListBinding

class LikeList: AppCompatActivity() {
    lateinit var binding: MypageLikeListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MypageLikeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뒤로가기 버튼
        binding.backspaceBtn.setOnClickListener {
            super.onBackPressed()
        }

        //gridview
        val myLayoutManager = GridLayoutManager(this, 3)
        binding.recyclerView.layoutManager = myLayoutManager
    }
}
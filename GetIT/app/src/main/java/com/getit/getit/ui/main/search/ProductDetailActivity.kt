package com.getit.getit.ui.main.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.getit.getit.databinding.FragmentProductDetailBinding

class ProductDetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var binding = FragmentProductDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var reviewDatas = ArrayList<Reviews>()
        // 더미데이터
        reviewDatas.apply {
            add(Reviews("이름1", "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리리리리릴리뷰뷰뷰뷰"))
            add(Reviews("이름1", "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용"))
            add(Reviews("이름1", "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용"))
            add(Reviews("이름1", "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용"))
            add(Reviews("이름1", "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용"))
            add(Reviews("이름1", "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용"))
            add(Reviews("이름1", "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용"))
            add(Reviews("이름1", "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용"))
            add(Reviews("이름1", "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용"))
            add(Reviews("이름1", "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용"))
            add(Reviews("이름1", "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용"))
        }

        val reviewRVAdatpter = ReviewRVAdatpter(reviewDatas)
        binding.productDetailReviewRv.adapter = reviewRVAdatpter
        binding.productDetailReviewRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
}
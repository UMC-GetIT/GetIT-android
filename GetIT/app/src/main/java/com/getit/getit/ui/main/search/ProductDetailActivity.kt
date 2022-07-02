package com.getit.getit.ui.main.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.getit.getit.databinding.FragmentProductDetailBinding
import com.google.gson.Gson

class ProductDetailActivity: AppCompatActivity() {
lateinit var binding: FragmentProductDetailBinding
    private var gson: Gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentProductDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = intent
        val productJson = intent.getStringExtra("product")
        val product = gson.fromJson(productJson, Products::class.java)
        setInit(product)


        var reviewDatas = ArrayList<Reviews>()
        // 더미데이터
        reviewDatas.apply {
            add(Reviews("이름", "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리리리리릴리뷰뷰뷰뷰"))
            add(Reviews("이름", "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용"))
            add(Reviews("이름", "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용"))
            add(Reviews("이름", "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용"))
            add(Reviews("이름", "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용"))
            add(Reviews("이름", "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용"))
            add(Reviews("이름", "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용"))
        }

        val reviewRVAdatpter = ReviewRVAdatpter(reviewDatas)
        binding.productDetailReviewRv.adapter = reviewRVAdatpter
        binding.productDetailReviewRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.productDetailLikeOnIb.setOnClickListener {
            binding.productDetailLikeOnIb.visibility = View.INVISIBLE
            binding.productDetailLikeOffIb.visibility = View.VISIBLE
        }

        binding.productDetailLikeOffIb.setOnClickListener {
            binding.productDetailLikeOnIb.visibility = View.VISIBLE
            binding.productDetailLikeOffIb.visibility = View.INVISIBLE
        }

    }

    private fun setInit(product: Products) {
        binding.productDetailImgIv.setImageResource(product.coverImg!!)
        binding.productDetailProductNameTv.text = product.name
        binding.productDetailProductPriceTv.text = product.price.toString()

    }
}
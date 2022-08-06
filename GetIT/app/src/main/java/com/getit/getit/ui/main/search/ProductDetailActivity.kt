package com.getit.getit.ui.main.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.getit.getit.R
import com.getit.getit.databinding.ActivityProductDetailBinding
import com.getit.getit.databinding.ActivitySearchBinding
import com.getit.getit.ui.BaseActivity
import com.google.gson.Gson
import java.text.DecimalFormat

class ProductDetailActivity: BaseActivity<ActivityProductDetailBinding>(ActivityProductDetailBinding::inflate) {
    private var gson: Gson = Gson()

    override fun initAfterBinding() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.backspaceBtn.setOnClickListener {
            backspace()
        }

        val intent = intent
        val productJson = intent.getStringExtra("product")
        val product = gson.fromJson(productJson, Products::class.java)
        setInit(product)


        var reviewDatas = ArrayList<Reviews>()
        // 더미데이터
        reviewDatas.apply {
            add(Reviews(R.drawable.dummy_review_img ,"재니퍼", "역대급 성능입니다! 웬만한 작업으로는 팬도 안 돌아가서 소음도 느껴지지 않네요. 강추합니다! 역대급 성능입니다! 웬만한 작업으로는 팬도 안 돌아가서 소음도 느껴지지 않네요. 강추합니다!"))
            add(Reviews(R.drawable.dummy_review_img ,"지온", "저는 포토샵이나 파이널컷 프로 정도 살짝 건드리는 수준인데 약간 오버스펙인 면이 없지 않아있지만 만족합니다."))
            add(Reviews(R.drawable.dummy_review_img ,"설기", "상자 뜯는 재미가 있어서 좋았습니다. 성능이 상상을 초월하네요 기대하고 구매했는데 잘 사용하겠습니다."))
        }

        val reviewRVAdatpter = ReviewRVAdatpter(reviewDatas)
        binding.productDetailReviewRv.adapter = reviewRVAdatpter
        binding.productDetailReviewRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

//        binding.productDetailLikeOnIb.setOnClickListener {
//            binding.productDetailLikeOnIb.visibility = View.INVISIBLE
//            binding.productDetailLikeOffIb.visibility = View.VISIBLE
//        }
//
//        binding.productDetailLikeOffIb.setOnClickListener {
//            binding.productDetailLikeOnIb.visibility = View.VISIBLE
//            binding.productDetailLikeOffIb.visibility = View.INVISIBLE
//        }

    }

    private fun setInit(product: Products) {
        binding.productDetailImgIv.setImageResource(product.coverImg!!)
        binding.productDetailProductNameTv.text = product.name
        // 천 단위 콤마 넣기
        var price = product.price
        val dec = DecimalFormat("#,###")
        binding.productDetailProductPriceTv.text = dec.format(price).toString() + "원"

    }
}
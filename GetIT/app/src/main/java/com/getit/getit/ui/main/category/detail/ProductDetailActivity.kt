package com.getit.getit.ui.main.category.detail

import android.content.Intent
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.getit.getit.databinding.ActivityProductDetailBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.ui.main.category.*

class ProductDetailActivity: BaseActivity<ActivityProductDetailBinding>(ActivityProductDetailBinding::inflate), ProductDetailView {
    private lateinit var productId: String
    private lateinit var sideImageRVAdapter: SideImageRVAdapter
    private lateinit var infoRVAdapter: InformationRVAdapter

    override fun initAfterBinding() {
        binding.backspaceBtn.setOnClickListener {
            backspace()
        }

        if (intent.hasExtra("productId")) {
            productId = intent.getStringExtra("productId").toString()
            binding.productDetailProductPriceTv.text = intent.getStringExtra("price").toString()
            val imageUrl = intent.getStringExtra("imageUrl").toString()
            if (imageUrl == "" || imageUrl == null) {
            }
            else {
                Glide.with(this).load(imageUrl).into(binding.productDetailImgIv)
            }
        }

        getProductDetail(productId)

//        var reviewDatas = ArrayList<Reviews>()
//        val reviewRVAdatpter = ReviewRVAdatpter(reviewDatas)
//        binding.productDetailReviewRv.adapter = reviewRVAdatpter
//        binding.productDetailReviewRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.productDetailLikeBtnOffIb.setOnClickListener {
            likeOn()
        }
        binding.productDetailLikeBtnOnIb.setOnClickListener {
            likeOff()
        }

        binding.productDetailReviewInputEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var userinput = binding.productDetailReviewInputEt.text.toString()
                binding.productDetailConfirmBtnOn.visibility = View.VISIBLE
                binding.productDetailConfirmBtnOff.visibility = View.INVISIBLE
                if (userinput.length.toString() == "0") {
                    binding.productDetailConfirmBtnOn.visibility = View.INVISIBLE
                    binding.productDetailConfirmBtnOff.visibility = View.VISIBLE
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun getProductDetail(id: String) {
        val categoryService = CategoryService()
        categoryService.setProductDetailView(this)
        categoryService.getproductDetail(id)
    }


    private fun likeOn() {
        binding.productDetailLikeBtnOnIb.visibility = View.VISIBLE
        binding.productDetailLikeBtnOffIb.visibility = View.INVISIBLE
    }
    private fun likeOff() {
        binding.productDetailLikeBtnOnIb.visibility = View.INVISIBLE
        binding.productDetailLikeBtnOffIb.visibility = View.VISIBLE
    }

    override fun onGetProductDetailLoading() {
        Log.d("PRODUCT-DETAIL", "로딩중")
    }

    override fun onGetProductDetailSuccess(Code: Int, result: ProductDetailResult) {
        binding.productDetailProductNameTv.text = result.name
        binding.productDetailProductTypeTv.text = result.type
        binding.productDetailPurchaseBtn.setOnClickListener {
            var purchaseLink = result.link
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(purchaseLink)))
        }

        // 이미지 리스트
        val images = result.photolist
        Log.d("PRODUCT-DETAIL", images.toString())
        sideImageRVAdapter = SideImageRVAdapter(this, images)
        binding.productDetailSideImagesRv.adapter = sideImageRVAdapter

        sideImageRVAdapter.setMyItemClickListener(object : SideImageRVAdapter.MyItemClickListener{
            override fun onItemClick(imageUrl: String) {
                Glide.with(applicationContext).load(imageUrl).into(binding.productDetailImgIv)
            }
        })

        // 상세 정보
        // 2차원 리스트에 index 0 = title, index 1 = content로 넣기
//        var infoTitle = mutableListOf("제조사", "등록일", "CPU", "CPU 속도", "코어 종류",
//            "화면 크기", "RAM", "저장용량", "통신규격", "운영체제",
//            "SSD", "HDD", "출력", "단자 종류")
//        var infoContent = mutableListOf(result.brand, result.date, result.cpu, result.cpurate, result.core,
//            result.size, result.ram, result.innermemory, result.communication, result.os,
//            result.ssd, result.hdd, result.output, result.terminal)

        var productInfo = mutableListOf(
            mutableListOf("제조사", result.brand),
            mutableListOf("등록일", result.date),
            mutableListOf("CPU", result.cpu),
            mutableListOf("CPU 속도", result.cpurate),
            mutableListOf("코어 종류", result.core),
            mutableListOf("화면 크기", result.size),
            mutableListOf("RAM", result.ram),
            mutableListOf("저장용량", result.innermemory),
            mutableListOf("통신규격", result.communication),
            mutableListOf("운영체제", result.os),
            mutableListOf("SSD", result.ssd),
            mutableListOf("HDD", result.hdd),
            mutableListOf("출력", result.output),
            mutableListOf("단자 종류", result.terminal)
        )

        for (i in productInfo.size - 1 downTo 0) {
            if (productInfo[i][1] == "미상") {
                productInfo.removeAt(i)
            }
        }
        Log.d("TEST", productInfo.toString())

        infoRVAdapter = InformationRVAdapter(this, productInfo)
        binding.productDetailInformationRv.adapter = infoRVAdapter
    }

    override fun onGetProductDetailFailure(Code: Int, message: String) {
        Log.d("PRODUCT-DETAIL", "로딩 실패")
    }
}
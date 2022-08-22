package com.getit.getit.ui.main.category.detail

import android.content.Intent
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.getit.getit.databinding.ActivityProductDetailBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.ui.main.category.CategoryService
import com.getit.getit.ui.main.category.Products
import com.getit.getit.ui.main.category.ReviewRVAdatpter
import com.getit.getit.ui.main.category.Reviews
import com.google.gson.Gson

class ProductDetailActivity: BaseActivity<ActivityProductDetailBinding>(ActivityProductDetailBinding::inflate), ProductDetailView {
    private lateinit var productId: String

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
        binding.productDetailPurchaseBtn.setOnClickListener {
            var purchaseLink = result.link
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(purchaseLink)))
        }

    }

    override fun onGetProductDetailFailure(Code: Int, message: String) {
        Log.d("PRODUCT-DETAIL", "로딩 실패")
    }
}
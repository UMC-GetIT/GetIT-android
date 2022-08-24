package com.getit.getit.ui.main.category.detail

import android.content.Intent
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.getit.getit.R
import com.getit.getit.databinding.ActivityProductDetailBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.ui.main.category.*

class ProductDetailActivity: BaseActivity<ActivityProductDetailBinding>(ActivityProductDetailBinding::inflate), ProductDetailView, LikeView {
    private lateinit var productId: String
    private lateinit var sideImageRVAdapter: SideImageRVAdapter
    private lateinit var infoRVAdapter: InformationRVAdapter

    private var isLiked: Boolean = false

    override fun initAfterBinding() {
        binding.backspaceBtn.setOnClickListener {
            backspace()
        }
//        isLiked = isLikedProduct()
        setInit()
        setOnClickListeners()
    }

    // 좋아요 클릭/해제
    private fun setLikeProduct(productId: String) {
        val likeService = ProductsService()
        likeService.setLike(this)
        likeService.like(productId)
    }

//    // db 좋아요 여부
//    private fun isLikedProduct(productId: String): Boolean {
//    }

    private fun setOnClickListeners() {
        binding.productDetailLikeBtnIb.setOnClickListener {
            if(isLiked) {
                binding.productDetailLikeBtnIb.setImageResource(R.drawable.ic_like_button_off)
            }
            else {
                binding.productDetailLikeBtnIb.setImageResource(R.drawable.ic_like_button_on)
            }
            setLikeProduct(productId)
        }
    }

   private fun setInit() {
        if (intent.hasExtra("productId")) {
            productId = intent.getStringExtra("productId").toString()
            binding.productDetailProductPriceTv.text = intent.getStringExtra("price").toString()
            val imageUrl = intent.getStringExtra("imageUrl").toString()
            if (imageUrl == "" || imageUrl == null) {
            } else {
                Glide.with(this).load(imageUrl).into(binding.productDetailImgIv)
            }
        }

        if (isLiked) {
            binding.productDetailLikeBtnIb.setImageResource(R.drawable.ic_like_button_on)
        } else {
            binding.productDetailLikeBtnIb.setImageResource(R.drawable.ic_like_button_off)
        }

       changeButtonColor()
    }

    private fun getProductDetail(id: String) {
        val categoryService = ProductsService()
        categoryService.setProductDetailView(this)
        categoryService.getproductDetail(id)
    }

    private fun changeButtonColor() {
        binding.productDetailReviewInputEt.addTextChangedListener(object : TextWatcher {
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

//        sideImageRVAdapter.setMyItemClickListener(object : SideImageRVAdapter.MyItemClickListener{
//            override fun onItemClick(imageUrl: String) {
//                Glide.with(applicationContext).load(imageUrl).into(binding.productDetailImgIv)
//            }
//        })

        // 상세정보
        var productInfo = getProductInfo(result)
        infoRVAdapter = InformationRVAdapter(this, productInfo)
        binding.productDetailInformationRv.adapter = infoRVAdapter
    }

    override fun onGetProductDetailFailure(Code: Int, message: String) {
        Log.d("PRODUCT-DETAIL", "로딩 실패")
    }

    private fun getProductInfo(result: ProductDetailResult): MutableList<MutableList<String>> {
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
        return productInfo
    }

    override fun onGetLikeSuccess(Code: Int, result: String) {
        Log.d("LIKE", result)
        showToast(result)
    }

    override fun onGetLikeFailure(Code: Int, message: String) {
        Log.d("LIKE", "통신 실패")
    }
}

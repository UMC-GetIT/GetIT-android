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
import com.getit.getit.ui.login.getJwt
import com.getit.getit.ui.main.category.*
import com.getit.getit.ui.main.category.detail.review.CreateReviewView
import com.getit.getit.ui.main.category.detail.review.ReviewListRVAdatpter
import com.getit.getit.ui.main.category.detail.review.ReviewListResult
import com.getit.getit.ui.main.category.detail.review.ReviewListView
import com.getit.getit.ui.main.category.detail.Like.IsLike
import com.getit.getit.ui.main.category.detail.Like.IsLikeView
import com.getit.getit.ui.main.category.detail.Like.LikeView

class ProductDetailActivity: BaseActivity<ActivityProductDetailBinding>(ActivityProductDetailBinding::inflate),
    ProductDetailView, IsLikeView, LikeView, ReviewListView, CreateReviewView {
    private lateinit var productId: String
    private lateinit var sideImageRVAdapter: SideImageRVAdapter
    private lateinit var infoRVAdapter: InformationRVAdapter
    private lateinit var reviewListRVAdatpter: ReviewListRVAdatpter

    private var isLiked: Boolean = false

    override fun initAfterBinding() {
        binding.backspaceBtn.setOnClickListener {
            backspace()
        }
        setInit()
        setOnClickListeners()
    }

    // 좋아요 클릭/해제
    private fun setLikeProduct(productId: String) {
        Log.d("TEST", "좋아요 클릭")
        val likeService = ProductsService()
        likeService.setLike(this)
        likeService.like(productId)
    }

    // db 좋아요 여부
    private fun isLikedProduct(productId: String) {
        val isLikeService = ProductsService()
        isLikeService.setIsLike(this)
        isLikeService.isLike(productId)
    }

    private fun setOnClickListeners() {
        binding.productDetailLikeBtnIb.setOnClickListener {
            if(isLiked) {
                binding.productDetailLikeBtnIb.setImageResource(R.drawable.ic_like_button_off)
                setLikeProduct(productId)
            }
            else {
                binding.productDetailLikeBtnIb.setImageResource(R.drawable.ic_like_button_on)
                setLikeProduct(productId)
            }
            setLikeProduct(productId)
        }
    }

   private fun setInit() {
        if (intent.hasExtra("productId")) {
            productId = intent.getStringExtra("productId").toString()
            binding.productDetailProductNameTv.text = intent.getStringExtra("productName").toString()
            binding.productDetailProductPriceTv.text = intent.getStringExtra("price").toString()
            Log.d("TEST", "$productId")
            val imageUrl = intent.getStringExtra("imageUrl").toString()
            if (imageUrl == "" || imageUrl == null) {
            } else {
                Glide.with(this).load(imageUrl).into(binding.productDetailImgIv)
            }
            getProductDetail(productId)
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

    private fun getReviews(productId: String) {
        val reviewListService = ProductsService()
        reviewListService.setReviewListView(this)
        reviewListService.getReviews(productId)
    }

    private fun createReview() {
        var review = binding.productDetailReviewInputEt.text.toString()
        val createReviewService = ProductsService()
        createReviewService.setCreateReviewView(this)
        createReviewService.createReview(productId, review)
    }

    // 제품 상세정보 서버 연결
    override fun onGetProductDetailLoading() {
        Log.d("PRODUCT-DETAIL", "로딩중")
        // 터치 막기 OR 로딩 중 띄우기
    }

    override fun onGetProductDetailSuccess(Code: Int, result: ProductDetailResult) {
        // 기존 좋아요 여부
        Log.d("TEST", "$productId, ${getJwt()}")
        isLikedProduct(productId)
        // 기존 리뷰 리스트
        getReviews(productId)

        binding.productDetailProductNameTv.text = result.name
        binding.productDetailProductTypeTv.text = result.type
        binding.productDetailPurchaseBtn.setOnClickListener {
            var purchaseLink = result.link
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(purchaseLink)))
        }

        // 이미지 리스트
        val images = result.photolist
        sideImageRVAdapter = SideImageRVAdapter(this, images)
        binding.productDetailSideImagesRv.adapter = sideImageRVAdapter

        // 이미지 클릭 시 메인 이미지 변경
//        sideImageRVAdapter.setMyItemClickListener(object : SideImageRVAdapter.MyItemClickListener{
//            override fun onItemClick(imageUrl: String) {
//                Glide.with(applicationContext).load(imageUrl).into(binding.productDetailImgIv)
//            }
//        })

        // 상세정보
        var productInfo = getProductInfo(result)
        infoRVAdapter = InformationRVAdapter(this, productInfo)
        binding.productDetailInformationRv.adapter = infoRVAdapter

        infoRVAdapter = InformationRVAdapter(this, productInfo)
        binding.productDetailInformationRv.adapter = infoRVAdapter

        // 리뷰 작성
        binding.productDetailConfirmBtnFrameLayout.setOnClickListener {
            createReview()
            getReviews(productId)
        }
    }

    override fun onGetProductDetailFailure(Code: Int, message: String) {
        Log.d("PRODUCT-DETAIL", "$message")
    }

    private fun getProductInfo(result: ProductDetailResult): MutableList<MutableList<String>> {
        var productInfo = mutableListOf(
            mutableListOf(getString(R.string.brand), result.brand),
            mutableListOf(getString(R.string.date), result.date),
            mutableListOf(getString(R.string.cpurate), result.cpurate),
            mutableListOf(getString(R.string.cpu), result.cpu),
            mutableListOf(getString(R.string.core), result.core),
            mutableListOf(getString(R.string.size), result.size),
            mutableListOf(getString(R.string.ram), result.ram),
            mutableListOf(getString(R.string.innermemory), result.innermemory),
            mutableListOf(getString(R.string.communication), result.communication),
            mutableListOf(getString(R.string.os), result.os),
            mutableListOf(getString(R.string.ssd), result.ssd),
            mutableListOf(getString(R.string.hdd), result.hdd),
            mutableListOf(getString(R.string.output), result.output),
            mutableListOf(getString(R.string.terminal), result.terminal)
        )

        for (i in productInfo.size - 1 downTo 0) {
            if (productInfo[i][1] == "미상") {
                productInfo.removeAt(i)
            }
        }
        return productInfo
    }

    // 좋아요
    override fun onGetLikeSuccess(Code: Int, result: String) {
        Log.d("LIKE", result)
        showToast(result)
    }

    override fun onGetLikeFailure(Code: Int, message: String) {
        Log.d("LIKE", "로딩 실패ㅜㅜ")
    }

    override fun onIsLikeSuccess(Code: Int, result: IsLike) {
        isLiked = result.isLike
        Log.d("TEST", "기존 좋아요 여부: $isLiked")
    }

    override fun onIsLikeFailure(Code: Int, message: String) {
        Log.d("IS-LIKE", "$message")
    }

    // 리뷰
    override fun onGetReviewLoading() {
        Log.d("REVIEW-LIST", "로딩중")
    }

    override fun onGetReviewSuccess(Code: Int, result: List<ReviewListResult>) {
        Log.d("REVIEW-LIST", "리뷰 리스트 불러오기 성공!")
        initRecyclerView(result)
    }

    override fun onGetReviewFailure(Code: Int, message: String) {
        Log.d("REVIEW-LIST", "로딩 실패ㅜㅜ")
    }

    private fun initRecyclerView(result: List<ReviewListResult>) {
        reviewListRVAdatpter = ReviewListRVAdatpter(this, result)
        binding.productDetailReviewRv.adapter = reviewListRVAdatpter
    }

    override fun onCreateReviewLoading() {
        TODO("Not yet implemented")
    }

    override fun onCreateReviewSuccess(Code: Int, result: String) {
        TODO("Not yet implemented")
    }

    override fun onCreateReviewFailure(Code: Int, message: String) {
        TODO("Not yet implemented")
    }
}

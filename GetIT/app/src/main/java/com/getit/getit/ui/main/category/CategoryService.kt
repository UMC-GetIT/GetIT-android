package com.getit.getit.ui.main.category

import android.util.Log
import com.getit.getit.ui.main.category.detail.ProductDetailResponse
import com.getit.getit.ui.main.category.detail.ProductDetailView
import com.getit.getit.ui.main.category.detail.review.ReviewResponse
import com.getit.getit.ui.main.category.detail.review.ReviewView
import com.getit.getit.ui.main.searchproduct.RecommendResponse
import com.getit.getit.ui.main.searchproduct.RecommendView
import com.getit.getit.utils.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryService {
    private lateinit var categorySearchView: CategorySearchView
    private lateinit var recommendView: RecommendView
    private lateinit var productDetailView: ProductDetailView
    private lateinit var reviewView: ReviewView

    fun setSearchView(categorySearchView: CategorySearchView){
        this.categorySearchView = categorySearchView
    }

    fun setRecommendView(recommendView: RecommendView) {
        this.recommendView = recommendView
    }

    fun setProductDetailView(productDetailView: ProductDetailView) {
        this.productDetailView = productDetailView
    }

    fun setReviewView(reviewView: ReviewView) {
        this.reviewView = reviewView
    }

    fun getCategory(type: String, requirement: String){
        val categoryService = getRetrofit().create(CategoryRetrofitInterface::class.java)

        categorySearchView.onGetCategoryLoading()

        categoryService.getCategory(type, requirement).enqueue(object: Callback<CategoryResponse> {
            override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                Log.d("TEST", response.toString())
                if(response.isSuccessful && response.code() == 200) {
                    val categoryResponse: CategoryResponse = response.body()!!
                    Log.d("CATEGORY-RESPONSE/SUCCESS", categoryResponse.toString())

                    when(val code = categoryResponse.code){
                        1000 -> categorySearchView.onGetCategorySuccess(code, categoryResponse.result)
                        else -> categorySearchView.onGetCategoryFailure(code, categoryResponse.message)
                    }
                }
            }
            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                Log.d("CATEGORY-RESPONSE/FAILURE", t.message.toString())
                categorySearchView.onGetCategoryFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })

        Log.d("CATEGORY", "HELLO")
    }

    fun getRecommend(){
        val recommendService = getRetrofit().create(CategoryRetrofitInterface::class.java)

        recommendService.getRecommend().enqueue(object: Callback<RecommendResponse>{
            override fun onResponse(call: Call<RecommendResponse>, response: Response<RecommendResponse>) {
                if(response.isSuccessful && response.code() == 200) {
                    val recommendResponse: RecommendResponse = response.body()!!

                    when (val code = recommendResponse.code){
                        1000 -> recommendView.onGetRecommendSuccess(code, recommendResponse.result)
                        else -> recommendView.onGetRecommendFailure(code, recommendResponse.message)
                    }
                }
            }
            override fun onFailure(call: Call<RecommendResponse>, t: Throwable) {
                Log.d("RECOMMEND/FAILURE", t.message.toString())
            }
        })
        Log.d("RECOMMEND", "HELLO")
    }

    fun getproductDetail(productIdx: String) {
        val productDetailService = getRetrofit().create(CategoryRetrofitInterface::class.java)

        productDetailService.getProductDetail(productIdx).enqueue(object: Callback<ProductDetailResponse>{
            override fun onResponse(call: Call<ProductDetailResponse>, response: Response<ProductDetailResponse>) {
                if(response.isSuccessful && response.code() == 200) {
                    val productDetailResponse: ProductDetailResponse = response.body()!!

                    when (val code = productDetailResponse.code){
                        1000 -> productDetailView.onGetProductDetailSuccess(code, productDetailResponse.result)
                        else -> productDetailView.onGetProductDetailFailure(code, productDetailResponse.message)
                    }
                }
            }
            override fun onFailure(call: Call<ProductDetailResponse>, t: Throwable) {
                Log.d("PRODUCT-DETAIL/FAILURE", t.message.toString())
            }
        })
        Log.d("PRODUCT-DETAIL", "HELLO")
    }

    fun getReviews(productIdx: String) {
        val reviewService = getRetrofit().create(CategoryRetrofitInterface::class.java)

        reviewService.getReviews(productIdx).enqueue(object: Callback<ReviewResponse>{
            override fun onResponse(call: Call<ReviewResponse>, response: Response<ReviewResponse>) {
                if(response.isSuccessful && response.code() == 200) {
                    val reviewResponse: ReviewResponse = response.body()!!

                    when (val code = reviewResponse.code){
                        1000 -> reviewView.onGetReviewSuccess(code, reviewResponse.result)
                        else -> reviewView.onGetReviewFailure(code, reviewResponse.message)
                    }
                }
            }
            override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                Log.d("REVIEW/FAILURE", t.message.toString())
            }
        })
        Log.d("REVIEW", "HELLO")
    }
}
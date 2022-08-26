package com.getit.getit.ui.main.category

import com.getit.getit.data.Review
import com.getit.getit.ui.main.category.detail.Like.IsLikeResponse
import com.getit.getit.ui.main.category.detail.Like.LikeResponse
import com.getit.getit.ui.main.category.detail.ProductDetailResponse
import com.getit.getit.ui.main.category.detail.review.CreateReviewResponse
import com.getit.getit.ui.main.category.detail.review.ReviewListResponse
import com.getit.getit.ui.main.searchproduct.RecommendResponse
import retrofit2.Call
import retrofit2.http.*

interface ProductsRetrofitInterface {
    @GET("/products/category")
    fun getCategory(
        @Query("type") type: String,
        @Query("requirement") requirement: String
    ): Call<CategoryResponse>

    @GET("/products/recommend")
    fun getRecommend(): Call<RecommendResponse>

    @GET("/products/{productIdx}")
    fun getProductDetail(
        @Path("productIdx") type: String
    ): Call<ProductDetailResponse>

    @GET("/products/isLike")
    fun isLike(
        @Query("productId") type: String
    ): Call<IsLikeResponse>

    @POST("/products/setLike")
    fun like(@Body productId : String): Call<LikeResponse>

    @GET("/products/review/{productIdx}")
    fun getReviews(
        @Path("productIdx") type: String
    ): Call<ReviewListResponse>

    @POST("/products/review")
    fun createReview(
        @Body review: Review
    ): Call<CreateReviewResponse>
}
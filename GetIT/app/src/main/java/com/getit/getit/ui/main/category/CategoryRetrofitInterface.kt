package com.getit.getit.ui.main.category

import com.getit.getit.data.Category
import com.getit.getit.ui.main.category.detail.ProductDetailResponse
import com.getit.getit.ui.main.searchproduct.RecommendResponse
import retrofit2.Call
import retrofit2.http.*

interface CategoryRetrofitInterface {
//    /products/category?type=&requirement=
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

//    @POST("/products/setLike")
}
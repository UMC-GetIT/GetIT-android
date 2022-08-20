package com.getit.getit.ui.main.category

import com.getit.getit.data.Category
import com.getit.getit.ui.main.searchproduct.RecommendResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CategoryRetrofitInterface {
//    /products/category?type=&requirement=
    @GET("/products/category")
    fun getCategory(
        @Query("type") type: String,
        @Query("requirement") requirement: String
    ): Call<CategoryResponse>

    @GET("/products/recommend")
    fun getRecommend(): Call<RecommendResponse>

//    @POST("/products/setLike")
}
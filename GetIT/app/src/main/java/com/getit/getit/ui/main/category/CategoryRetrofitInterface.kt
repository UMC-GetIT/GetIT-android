package com.getit.getit.ui.main.category

import com.getit.getit.data.Category
import com.getit.getit.ui.main.searchproduct.RecommendResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryRetrofitInterface {
    @GET("/products/category")
    fun getCategory(
        @Query("category") category: Category
    ): Call<CategoryResponse>

    @GET("/products/recommend")
    fun getRecommend(): Call<RecommendResponse>
}
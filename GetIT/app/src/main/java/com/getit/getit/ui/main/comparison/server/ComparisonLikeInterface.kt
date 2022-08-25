package com.getit.getit.ui.main.comparison.server

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ComparisonLikeInterface {
    @GET("/users/mylike")
    fun getLikedProducts(
        @Query("type") type: String
    ): Call<ComparisonLikeResponse>

}
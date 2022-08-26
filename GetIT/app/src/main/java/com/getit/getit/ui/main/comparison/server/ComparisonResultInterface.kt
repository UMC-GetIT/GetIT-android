package com.getit.getit.ui.main.comparison.server

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ComparisonResultInterface {
    @GET("/products/comparison/{productIdx1}/{productIdx2}")
    fun getComparisonResultProducts(
        @Path("productIdx1") productIdx1: String,
        @Path("productIdx2") productIdx2: String
    ): Call<ComparisonResultResponse>

}
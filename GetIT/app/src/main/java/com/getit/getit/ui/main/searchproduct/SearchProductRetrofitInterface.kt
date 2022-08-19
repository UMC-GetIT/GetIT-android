package com.getit.getit.ui.main.searchproduct

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchProductRetrofitInterface {
    @GET("/api/search")
    fun getSearchProducts(@Query("query") keyword: String): Call<SearchProductResponse>
}